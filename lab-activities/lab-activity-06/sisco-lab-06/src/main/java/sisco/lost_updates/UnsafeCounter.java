package sisco.lost_updates;

/**
 * 
 * This class is *NOT* Thread-safe
 * 
 */
public class UnsafeCounter implements Counter {

	private int cont;

	public UnsafeCounter(int base) {
		this.cont = base;
	}

	public void inc() {
		cont++;
	}

	public int getValue() {
		return cont;
	}
}
