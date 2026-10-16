package sisco.how_many_threads;

import java.util.concurrent.atomic.AtomicInteger;

public class TestHowManyThreads {
	
	public static void main(String[] args) {
		AtomicInteger counter = new AtomicInteger(0);			
		try {
			while (true) {
				new Thread(() -> {
					System.out.println("Hello from " + Thread.currentThread().getName());
					counter.addAndGet(1);					
					try {
						Thread.sleep(100000);
					} catch (Exception ex) {}
				}).start();
			}
		} catch (java.lang.OutOfMemoryError ex) {
			// ex.printStackTrace();
		}
		System.out.println("Limit reached - num threads created: " + counter.intValue());
	}

}
