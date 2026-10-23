package sisco.hello_threads;

public class MyThread extends Thread {

	public MyThread(String myName){
		super(myName);
	}
	
	public void run(){
		log("Hello concurrent world!");
		log("Sleeping for 50 secs...");
		try {
			Thread.sleep(50000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		log("Done.");
	}
	
	private void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() +   " ][ " + getName()+ " ] " + msg); 
	}
}
