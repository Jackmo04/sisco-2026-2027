package sisco.concur_sort;

public class AbstractWorker extends Thread {
		
	public AbstractWorker(String name){
		super(name);	
	}
	
	protected void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() +  " ][ " + this.getName() + " ] " + msg); 
	}
	

}
