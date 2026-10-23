package sisco.lost_updates;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * This class is Thread-safe
 * 
 */
public class SafeCounter implements Counter {

	private int cont;
	private Lock lock;

	public SafeCounter(int base) {
		this.cont = base;
		this.lock = new ReentrantLock();
	}

	public void inc() {
		lock.lock();
		cont++;
		lock.unlock();
	}

	public int getValue() {
		lock.lock();
		var tmp = cont;
		lock.unlock();
		return tmp;
	}
}
