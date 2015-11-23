package com.vigenere.breaker.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VigenereBreakerImpl extends UnicastRemoteObject implements VigenereBreaker{

	private static final long serialVersionUID = 1453424354656L;
	private static Map<String, Long> quads = new HashMap<String, Long>();


	protected VigenereBreakerImpl() throws RemoteException {
		super();
	}

	@Override
	public String decrypt(String cypherText, int maxKeyLength){
		
		for (Entry<String, Long> entry : quads.entrySet()) {
		    String key = entry.getKey();
		    long value = entry.getValue();
		    System.out.println(key + " " + value + "\n");
		}
		return null;
	}
	
	public static void main(String[] args) throws RemoteException{
						
		try { //special exception handler for registry creation
            LocateRegistry.createRegistry(8999); 
            System.setProperty("java.security.policy","file:.res/rmi.policy");
            System.out.println("java RMI registry created.");
            
            VigenereBreaker vigenereBreaker = new VigenereBreakerImpl();
    		Naming.bind("//localhost/VB", vigenereBreaker);
        } catch (RemoteException e) {//do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		
		try {
						
			File quadgramFile = new File("res/quadgrams.txt");
			BufferedReader buffer = new BufferedReader(new FileReader(quadgramFile));
			
			String line;
			while((line = buffer.readLine()) != null){
				String firstFour = line.substring(0, 4);
				long occurs = Long.parseLong(line.substring(5, line.length()));
				quads.put(firstFour, occurs);
			}
			
			buffer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new VigenereBreakerImpl().decrypt("asdsas", 3);
	}
}
