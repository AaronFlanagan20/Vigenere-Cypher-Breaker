package ie.gmit.sw.client;

public class Client {

	public static void main(String[] args) throws Exception {
		
		MessageRequest object = new MessageRequest(3, "hello", 1);
		
		InQueue queue = InQueue.getInstance();

		queue.addLast(object);
		queue.getSize();
				
		System.out.println("Checking job queue...");
		
		//MessageRequest messageRequest = InQueue.getFirst();
		//System.out.println("This is jobNumber: " + messageRequest.getJobNumber());
				
		/*
		 * Looks for remote object
		 */
		//VigenereBreaker breaker = (VigenereBreaker)Naming.lookup("//localhost/VB");
		
		
//		
//		//String result = breaker.decrypt(messageRequest.getCypherText(), messageRequest.getMaxKeyLength());//definitely pasing values
//		//System.out.println(result);
	}
	
}
