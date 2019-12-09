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

package org.apache.flink.streaming.connectors.kafka.config;

import org.apache.flink.annotation.Internal;

/**
 * Utilities for {@link OffsetCommitMode}.
 */
@Internal
public class OffsetCommitModes {

	/**
	 * Determine the offset commit mode using several configuration values.
	 * 使用几个配置值确定偏移提交模式。
	 *
	 * @param enableAutoCommit whether or not auto committing is enabled in the provided Kafka properties.
	 * @param enableCommitOnCheckpoint whether or not committing on checkpoints is enabled.
	 * @param enableCheckpointing whether or not checkpoint is enabled for the consumer. 是否为消费者启用检查点。
	 *
	 * @return the offset commit mode to use, based on the configuration values.
	 */
	public static OffsetCommitMode fromConfiguration(
			boolean enableAutoCommit,
			boolean enableCommitOnCheckpoint,
			boolean enableCheckpointing) {

		if (enableCheckpointing) {
			// if checkpointing is enabled, the mode depends only on whether committing on checkpoints is enabled
			//如果启用了检查点，那么该偏移提交模式仅取决于是否启用了在检查点上提交
			return (enableCommitOnCheckpoint) ? OffsetCommitMode.ON_CHECKPOINTS : OffsetCommitMode.DISABLED;
		} else {
			// else, the mode depends only on whether auto committing is enabled in the provided Kafka properties
			//该模式仅取决于是否在提供的Kafka属性中启用了自动提交
			return (enableAutoCommit) ? OffsetCommitMode.KAFKA_PERIODIC : OffsetCommitMode.DISABLED;
		}
	}
}
