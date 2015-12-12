package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * This is the Remote-Interface. By extending this VigenereBreakers method can be invoked by another JVM.
 * Any object that implements this interface can be a remote object
 * 
 * @author Aaron Flanagan
 */

public interface VigenereBreaker extends Remote {
	
	/*
	 * This is a remote-method. It throws a RemoteException is communication or a protoco fails
	 * @param String cypherText
	 * @param Int maxKeyLength
	 */
	public String decrypt(String cypherText, int maxKeyLength) throws RemoteException;
	
	public final static String lookUpName = "Vigenere";
	
}
