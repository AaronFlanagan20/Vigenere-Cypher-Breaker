package ie.gmit.sw.client;

public class MessageRequest {
	
	private int maxKeyLength;
	private String cypherText;
	private long jobNumber;
	
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
