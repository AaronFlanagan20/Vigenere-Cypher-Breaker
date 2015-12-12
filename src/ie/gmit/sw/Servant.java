package ie.gmit.sw;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/*
 *  Servant is client of the RMI_Server. It invokes a request to the remote object with text and keyLength
 * @see VigenereBreakerImpl
 * 
 * @author Aaron Flanagan
 */

public class Servant {
	
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {			
			public void run() {
				try {
					VigenereBreaker breaker = (VigenereBreaker) Naming.lookup(VigenereBreaker.lookUpName);
								
					String result = breaker.decrypt("JNOISRSZSIJBGIHQMZNIJRDACRSH", 5);
					System.out.println(result);
				} catch (MalformedURLException | RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			}
		});	
		thread.start();
	}
}
