package sisco.hello_threads;

public class Main {

	public static void main(String[] args) throws Exception {

		log("Hello from the main thread");
		log("Number of processors: " + Runtime.getRuntime().availableProcessors());
		log("Launching new threads...");

		var myThreadOne = new MyThread("ThreadOne");
		myThreadOne.start();		

		var myThreadTwo = new Thread(new MyRunnable("ThreadTwo"));
		myThreadTwo.start();		
		
		var t0 = System.currentTimeMillis();
		
		// var tnano = System.nanoTime();
		
		log("Threads spawned at: " + t0);
		log("Waiting for their termination.");

		/* launch also a clock thread, showing time elapsed */
		var clock = new SimpleClock(1000);
		clock.start();
		
		/* main thread blocks until the other threads terminate */
		myThreadOne.join();
		myThreadTwo.join();

		new Thread(() -> {
			log("Hello from a lambda thread");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				log("Lambda thread interrupted");
			}
		}).start();

		/* Notify clock to stop (deferred cancellation) -- "stop" method is deprecated */
		clock.notifyStop();
		
		var t1 = System.currentTimeMillis();
		log("Completed at " + t1 + " (" + (t1 - t0) + " secs)");
	}

	private static void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() +   " ][ " + Thread.currentThread().getName()+ " ] " + msg); 
	}

}
