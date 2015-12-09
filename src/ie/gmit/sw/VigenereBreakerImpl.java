package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class VigenereBreakerImpl extends UnicastRemoteObject implements VigenereBreaker{

	private static final long serialVersionUID = 1L;
	private KeyEnumerator breaker;

	public VigenereBreakerImpl() throws Exception {
		breaker = new KeyEnumerator();
	}

	public String decrypt(String cypherText, int maxKeyLength){
		return breaker.crackCypher(cypherText, maxKeyLength);
	}
	
	public static void main(String[] args) throws Exception{
						
        LocateRegistry.createRegistry(1099); 
        //System.setProperty("java.security.policy","file:.res/rmi.policy");
        System.out.println("java RMI registry created.");
            
        VigenereBreaker vigenereBreaker = new VigenereBreakerImpl();
		Naming.bind("//localhost/VB", vigenereBreaker);
      
		System.out.println("Started...");
	}
}
