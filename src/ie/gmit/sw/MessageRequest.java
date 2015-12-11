package ie.gmit.sw;

public class MessageRequest {

	private final int maxKeyLength;
	private final String cypherText;
	private final long jobNumber;

	public MessageRequest(int maxKeyLength, String cypherText, long jobNumber) {
		this.maxKeyLength = maxKeyLength;
		this.cypherText = cypherText;
		this.jobNumber = jobNumber;
	}

	public int getMaxKeyLength() {
		return maxKeyLength;
	}

	public String getCypherText() {
		return cypherText;
	}

	public long getJobNumber() {
		return jobNumber;
	}
}
