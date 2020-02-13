package window;

public class DeliveryMessageDto {

	private String messageBody;
	private String messageId;

	public DeliveryMessageDto() {
	}

	public String getMessageBody() {
		return this.messageBody;
	}

	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof DeliveryMessageDto)) {
			return false;
		} else {
			DeliveryMessageDto other = (DeliveryMessageDto)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$messageBody = this.getMessageBody();
				Object other$messageBody = other.getMessageBody();
				if (this$messageBody == null) {
					if (other$messageBody != null) {
						return false;
					}
				} else if (!this$messageBody.equals(other$messageBody)) {
					return false;
				}

				Object this$messageId = this.getMessageId();
				Object other$messageId = other.getMessageId();
				if (this$messageId == null) {
					if (other$messageId != null) {
						return false;
					}
				} else if (!this$messageId.equals(other$messageId)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof DeliveryMessageDto;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $messageBody = this.getMessageBody();
		result = result * 59 + ($messageBody == null ? 43 : $messageBody.hashCode());
		Object $messageId = this.getMessageId();
		result = result * 59 + ($messageId == null ? 43 : $messageId.hashCode());
		return result;
	}

	public String toString() {
		return "DeliveryMessageDto(messageBody=" + this.getMessageBody() + ", messageId=" + this.getMessageId() + ")";
	}
}
