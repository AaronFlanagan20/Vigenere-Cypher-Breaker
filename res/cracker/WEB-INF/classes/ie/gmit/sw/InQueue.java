package ie.gmit.sw;

import java.util.LinkedList;
import java.util.Queue;

public class InQueue{

	private Queue<MessageRequest> inQueue = new LinkedList<MessageRequest>();

	public boolean isEmpty() {
		return inQueue.isEmpty();
	}

	public boolean offer(MessageRequest e) {
		return inQueue.offer(e);
	}

	public MessageRequest poll() {
		return inQueue.poll();
	}

	public int size() {
		return inQueue.size();
	}

	

}
