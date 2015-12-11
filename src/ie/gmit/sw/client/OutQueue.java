package ie.gmit.sw.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OutQueue{

	private Map<Long, String> outQueue = new ConcurrentHashMap<Long, String>();
	
	private Thread add = null;
	private Thread remove = null;

	public void add(final long jobNumber, final String result){
		add = new Thread(new Runnable() {
			public void run() {
				outQueue.put(jobNumber, result);
			}
		});
		
		add.start();
	}

	public void remove(final long jobNumber){
		remove = new Thread(new Runnable() {
			public void run() {
				outQueue.remove(jobNumber);
			}
		});
		
		remove.start();
	}
	
	public String get(final long job){
		if(outQueue.containsKey(job)){
			return outQueue.get(job);
		}else{
			return null;
		}
	}
}
