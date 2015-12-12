package ie.gmit.sw.client;

/*
 * Immutable object that holds the keyLength, encrypted text annd jobNumber.
 * This object is the request objected put in and out of the queues
 */

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
