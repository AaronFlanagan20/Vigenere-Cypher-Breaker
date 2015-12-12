package ie.gmit.sw;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/*
 * VigenereBreakerImpl is our remote object, it implements the remote interface VigenereBreaker.
 * It extends UnicastRemoteObject so that it may be exported using JRMP.
 * 
 * VigenereBreakerImpl creates a keyenumerator, takes requests from clients and invokes the crackCypher method and returns the result
 *
 * @see UnicastRemoteObject
 * @see VigenereBreaker
 * @see KeyEnumerator
 * 
 * @author Aaron Flanagan
 */
public class VigenereBreakerImpl extends UnicastRemoteObject implements VigenereBreaker{

	private static final long serialVersionUID = 1L;
	private KeyEnumerator breaker;
	
	/*
	 * On creation VigenereBreakerImpl creates A KeyEnumerator for generating the key
	 * @see KeyEnumerator
	 */
	public VigenereBreakerImpl() throws Exception {
		breaker = new KeyEnumerator();
	}

	/*
	 * Clients pass in the text and key, this invokes KeyEnumerators crackCypher and returns the result
	 * @see VigenereBreaker.decrypt
	 */
	public String decrypt(String cypherText, int maxKeyLength){
		return breaker.crackCypher(cypherText, maxKeyLength);
	}
	
	public static void main(String[] args) throws Exception{
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					LocateRegistry.createRegistry(1099);
					//System.setProperty("java.security.policy","file:.res/rmi.policy");
			        System.out.println("java RMI registry created.");
			            
			        VigenereBreaker vigenereBreaker = new VigenereBreakerImpl();
					Naming.bind(VigenereBreaker.lookUpName, vigenereBreaker);
			      
					System.out.println("Started...");
				
				} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
		thread.start();		
	}
}
