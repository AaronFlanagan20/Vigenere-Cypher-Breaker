package ie.gmit.sw;

import ie.gmit.sw.client.InQueue;
import ie.gmit.sw.client.MessageRequest;

public class Servant {
	
	public static void main(String[] args) throws InterruptedException {
					
		MessageRequest object = new MessageRequest(3, "hello", 1);
		
		InQueue queue = InQueue.getInstance();

		queue.addLast(object);
		queue.getSize();
		
		
		Thread.sleep(100000);
	
	}
}
