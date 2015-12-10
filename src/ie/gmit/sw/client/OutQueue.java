package ie.gmit.sw.client;

import java.util.HashMap;
import java.util.Map;

public class OutQueue {
	
	private static Map<Integer, Object> outQueue = new HashMap<Integer, Object>();
	
	public void add(int jobNumber, Object object){
		outQueue.put(jobNumber, object);
	}
	
	public void remove(int jobNumber, Object object){
		outQueue.remove(jobNumber, object);
	}
	
}
