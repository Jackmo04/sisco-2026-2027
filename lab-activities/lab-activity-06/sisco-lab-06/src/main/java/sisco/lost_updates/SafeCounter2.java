package sisco.lost_updates;

/**
 * 
 * This class is Thread-safe
 * 
 */
public class SafeCounter2 implements Counter {

	private int cont;

	public SafeCounter2(int base) {
		this.cont = base;
	}

	public synchronized void inc() {
		cont++;
	}

	public synchronized int getValue() {
		return cont;
	}
}
