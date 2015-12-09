package ie.gmit.sw.client;

import java.rmi.Naming;

import ie.gmit.sw.VigenereBreaker;

public class Client {

	public static void main(String[] args) throws Exception {
		/*
		 * Looks for remote object
		 */
		VigenereBreaker breaker = (VigenereBreaker)Naming.lookup("//localhost/VB");
		
		System.out.println("Checking job queue...");
		MessageRequest message = new MessageRequest(5, "JNOISRBNBCJKURBQVNWCJARJWRBV", 1);//key should be javap
		InQueue.addLast(message);
		MessageRequest messageRequest = InQueue.getFirst();
		System.out.println("This is jobNumber: " + messageRequest.getJobNumber());
		
		String result = breaker.decrypt(messageRequest.getCypherText(), messageRequest.getMaxKeyLength());//definitely pasing values
		System.out.println(result);
	}
	
}
