package ie.gmit.sw.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * OutQueue holds the result passed back from the remote object
 */
public class OutQueue{

	private Map<Long, String> outQueue = new ConcurrentHashMap<Long, String>();
	
	private Thread add = null;
	private Thread remove = null;

	/*
	 * Adds the jobNumber and result from remote object
	 */
	public void add(final long jobNumber, final String result){
		add = new Thread(new Runnable() {
			public void run() {
				outQueue.put(jobNumber, result);
			}
		});
		
		add.start();
	}

	/*
	 * removes the jobNumber and result after displayed to client
	 */
	public void remove(final long jobNumber){
		remove = new Thread(new Runnable() {
			public void run() {
				outQueue.remove(jobNumber);
			}
		});
		
		remove.start();
	}
	
	/*
	 * Searches for the jobNumber and returns the result
	 */
	public String get(final long job){
		if(outQueue.containsKey(job)){
			return outQueue.get(job);
		}else{
			return null;
		}
	}
}
