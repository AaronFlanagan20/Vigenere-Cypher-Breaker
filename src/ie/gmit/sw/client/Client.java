package ie.gmit.sw.client;

import java.rmi.Naming;

import ie.gmit.sw.VigenereBreaker;

public class Client {

	public static void main(String[] args) throws Exception {
		/*
		 * Looks for remote object
		 */
		System.out.println("Looking for rmemote object../");
		VigenereBreaker breaker = (VigenereBreaker)Naming.lookup("//localhost/VB");
		System.out.println("found");
		
		String result = breaker.decrypt("assd", 6);
		System.out.println(result);
	}
	
}
