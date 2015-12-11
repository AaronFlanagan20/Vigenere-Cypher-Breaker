package ie.gmit.sw;

import java.rmi.Naming;

import ie.gmit.sw.VigenereBreaker;

public class Worker{
	
	private InQueue in;
	private OutQueue out;
	
	public Worker(InQueue in, OutQueue out) {
		this.in = in;
		this.out = out;
	}
	
	public void lookAndInvoke() throws Exception{
		VigenereBreaker breaker = (VigenereBreaker) Naming.lookup("//localhost/VB");
		
		while(!in.isEmpty()){
			MessageRequest messageRequest = in.poll();
			
			String result = breaker.decrypt(messageRequest.getCypherText(), messageRequest.getMaxKeyLength());
			System.out.println(result);
			out.add(messageRequest.getJobNumber(), result);
		}
	}
}
