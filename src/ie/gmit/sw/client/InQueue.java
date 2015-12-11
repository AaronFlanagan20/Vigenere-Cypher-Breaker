package ie.gmit.sw.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InQueue{
	
	private BlockingQueue<MessageRequest> inQueue = new ArrayBlockingQueue<MessageRequest>(10);
	
	private Thread add = null;
	private Thread remove = null;
	
	public void add(final MessageRequest messageRequest) {
		add = new Thread(new Runnable() {
			public void run() {
				try {
					inQueue.put(messageRequest);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}				
			}
		});
		
		add.start();
	}
	
	public MessageRequest poll() {
		return inQueue.poll();
	}

	public boolean contains(Object o) {
		return inQueue.contains(o);
	}

	public boolean isEmpty() {
		return inQueue.isEmpty();
	}

	public void remove(final Object object) {
		remove = new Thread(new Runnable() {
			public void run() {
				inQueue.remove(object);				
			}
		});
		
		remove.start();
	}

}
