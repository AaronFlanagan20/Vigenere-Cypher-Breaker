package ie.gmit.sw;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Servant {
	
	public static void main(String[] args) {
							
		try {
			VigenereBreaker breaker = (VigenereBreaker) Naming.lookup("//localhost/VB");
			
			MessageRequest messageRequest = new MessageRequest(5, "EDPIDMIASTERHISLCANTEHEANMII", 1);
			
			String result = breaker.decrypt(messageRequest.getCypherText(), messageRequest.getMaxKeyLength());
			System.out.println(result);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

			
	}
}
