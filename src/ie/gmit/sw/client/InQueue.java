package ie.gmit.sw.client;

import java.util.LinkedList;

public class InQueue{
	
	private static LinkedList<MessageRequest> inQueue = new LinkedList<MessageRequest>();

	public static void addLast(MessageRequest e) {
		inQueue.addLast(e);
	}

	public static MessageRequest getFirst() {
		return inQueue.getFirst();
	}

	public static MessageRequest removeFirst() {
		return inQueue.removeFirst();
	}
	
}
