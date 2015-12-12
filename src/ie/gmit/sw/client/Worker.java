package ie.gmit.sw.client;

import java.rmi.Naming;

import ie.gmit.sw.VigenereBreaker;
/*
 * Worker takes in a request on the InQueue, invokes the remote object method, and puts the result in the OutQueue
 * 
 * @author Aaron Flanagan
 */
public class Worker{
	
	private InQueue in;
	private OutQueue out;
	
	public Worker(InQueue in, OutQueue out) {
		this.in = in;
		this.out = out;
	}
	
	/*
	 * Looks for the remote object and invokes its decrypt method and returns the result
	 * It then puts the result in the OutQueue and removes it from the InQueue
	 */
	public void lookAndInvoke() throws Exception{
		VigenereBreaker breaker = (VigenereBreaker) Naming.lookup("//localhost/VB");
		
		while(!in.isEmpty()){
			MessageRequest messageRequest = in.poll();
			
			String result = breaker.decrypt(messageRequest.getCypherText(), messageRequest.getMaxKeyLength());
			System.out.println(result);
			out.add(messageRequest.getJobNumber(), result);
			in.remove(messageRequest);
		}
	}
}
