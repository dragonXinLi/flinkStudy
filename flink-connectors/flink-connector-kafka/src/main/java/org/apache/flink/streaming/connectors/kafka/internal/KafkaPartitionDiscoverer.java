/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.connectors.kafka.internal;

import org.apache.flink.annotation.Internal;
import org.apache.flink.streaming.connectors.kafka.internals.AbstractPartitionDiscoverer;
import org.apache.flink.streaming.connectors.kafka.internals.KafkaTopicPartition;
import org.apache.flink.streaming.connectors.kafka.internals.KafkaTopicsDescriptor;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.apache.flink.util.Preconditions.checkNotNull;

/**
 * A partition discoverer that can be used to discover topics and partitions metadata
 * from Kafka brokers via the Kafka high-level consumer API.
 */
@Internal
public class KafkaPartitionDiscoverer extends AbstractPartitionDiscoverer {

	private final Properties kafkaProperties;

	private KafkaConsumer<?, ?> kafkaConsumer;

	public KafkaPartitionDiscoverer(
		KafkaTopicsDescriptor topicsDescriptor,
		int indexOfThisSubtask,
		int numParallelSubtasks,
		Properties kafkaProperties) {

		super(topicsDescriptor, indexOfThisSubtask, numParallelSubtasks);
		this.kafkaProperties = checkNotNull(kafkaProperties);
	}

	@Override
	protected void initializeConnections() {
		//创建kafka消费者
		this.kafkaConsumer = new KafkaConsumer<>(kafkaProperties);
	}

	@Override
	protected List<String> getAllTopics() throws AbstractPartitionDiscoverer.WakeupException {
		try {
			return new ArrayList<>(kafkaConsumer.listTopics().keySet());
		} catch (org.apache.kafka.common.errors.WakeupException e) {
			// rethrow our own wakeup exception
			throw new AbstractPartitionDiscoverer.WakeupException();
		}
	}

	/**
	 * partitions列表里面存储的是 new KafkaTopicPartition对象，该对象包含各个分区所属的topic名称和分区号
	 * 这里返回的所有的topics的所有分区的topic名称和分区号，并不是一个topic的
	 *
	 * 如：
	 *
	 * 0 = {KafkaTopicPartition@6131} "KafkaTopicPartition{topic='user-behavior-pos-storeOrder', partition=2}"
	 * 1 = {KafkaTopicPartition@6132} "KafkaTopicPartition{topic='user-behavior-pos-storeOrder', partition=4}"
	 * 2 = {KafkaTopicPartition@6133} "KafkaTopicPartition{topic='user-behavior-pos-storeOrder', partition=1}"
	 * 3 = {KafkaTopicPartition@6134} "KafkaTopicPartition{topic='user-behavior-pos-storeOrder', partition=3}"
	 * 4 = {KafkaTopicPartition@6135} "KafkaTopicPartition{topic='user-behavior-pos-storeOrder', partition=0}"
	 *
	 * @param topics 需要的topic列表
	 * @return
	 * @throws AbstractPartitionDiscoverer.WakeupException
	 */
	@Override
	protected List<KafkaTopicPartition> getAllPartitionsForTopics(List<String> topics) throws AbstractPartitionDiscoverer.WakeupException {
		List<KafkaTopicPartition> partitions = new LinkedList<>();

		try {
			for (String topic : topics) {
				for (PartitionInfo partitionInfo : kafkaConsumer.partitionsFor(topic)) {
					partitions.add(new KafkaTopicPartition(partitionInfo.topic(), partitionInfo.partition()));
				}
			}
		} catch (org.apache.kafka.common.errors.WakeupException e) {
			// rethrow our own wakeup exception
			throw new AbstractPartitionDiscoverer.WakeupException();
		}

		return partitions;
	}

	@Override
	protected void wakeupConnections() {
		if (this.kafkaConsumer != null) {
			this.kafkaConsumer.wakeup();
		}
	}

	@Override
	protected void closeConnections() throws Exception {
		if (this.kafkaConsumer != null) {
			this.kafkaConsumer.close();

			// de-reference the consumer to avoid closing multiple times
			this.kafkaConsumer = null;
		}
	}

}
