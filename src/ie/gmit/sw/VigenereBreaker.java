package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VigenereBreaker extends Remote {
	
	/*
	 * Pass in the encrypted message and maximum length of decryption key
	 */
	public String decrypt(String cypherText, int maxKeyLength) throws RemoteException;
	
}
