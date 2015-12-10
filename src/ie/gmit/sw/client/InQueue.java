package ie.gmit.sw.client;

import java.util.LinkedList;

public class InQueue{
	
	private LinkedList<MessageRequest> inQueue = new LinkedList<MessageRequest>();
	private static InQueue queue = new InQueue();
	
	private InQueue() {}

	public static InQueue getInstance(){
		System.out.println(queue);
		System.out.println(queue.inQueue);
		return queue;
	}
	
	public void addLast(MessageRequest messageRequest) {
		if(inQueue.size() == 0){
			inQueue.add(messageRequest);
			System.out.println("List is initialized");
		}
		else{
			inQueue.addLast(messageRequest);
			System.out.println("added in the end");
		}
	}

	public MessageRequest getFirst() {
		return inQueue.getFirst();
	}

	public void removeFirst() {
		inQueue.removeFirst();
	}
	
	public void getSize(){
		System.out.println(inQueue.size());
	}

}
