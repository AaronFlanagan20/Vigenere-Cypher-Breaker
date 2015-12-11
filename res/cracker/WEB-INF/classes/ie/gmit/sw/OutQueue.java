package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

public class OutQueue {

	private Map<Long, Object> outQueue = new HashMap<Long, Object>();

	public void add(long jobNumber, Object object){
		outQueue.put(jobNumber, object);
	}

	public void remove(long jobNumber){
		outQueue.remove(jobNumber);
	}
	
	public boolean getJob(long job){
		return outQueue.keySet().contains(job);
	}
	
	

}
