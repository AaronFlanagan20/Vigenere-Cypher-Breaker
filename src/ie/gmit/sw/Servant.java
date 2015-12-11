package ie.gmit.sw;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Servant {
	
	public static void main(String[] args) {
		
		/*
		 * Servant looks for the remote object and once found it invokes its decrypt method and return the result
		 */
		try {
			VigenereBreaker breaker = (VigenereBreaker) Naming.lookup(VigenereBreaker.lookUpName);
						
			String result = breaker.decrypt("JNOISRSZSIJBGIHQMZNIJRDACRSH", 5);
			System.out.println(result);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

			
	}
}
