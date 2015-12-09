package ie.gmit.sw;

import ie.gmit.sw.client.InQueue;
import ie.gmit.sw.client.MessageRequest;

public class Servant {
	
	public static void main(String[] args) {
		MessageRequest object = new MessageRequest(5, "helldo", 3);
		InQueue.addLast(object);
	}
}
