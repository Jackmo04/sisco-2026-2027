package sisco.monitors.ex_latch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Latch - to be implemented
 */
public class FakeLatch implements Latch {

	private int nThread;
	private int count = 0;
	private Lock mutex;
	private Condition waitingCondition;

	public FakeLatch(int nThread) {
		this.nThread = nThread;
		this.mutex = new ReentrantLock();
		this.waitingCondition = mutex.newCondition();
	}
	
	@Override
	public void await() throws InterruptedException {
		try {
			mutex.lock();
			while (count < nThread) {
				waitingCondition.await();
			}
		} finally {
			mutex.unlock();
		}
	}

	@Override
	public void countDown() {
		try {
			mutex.lock();
			count++;
			if (count >= nThread) {
				waitingCondition.signalAll();
			}
		} finally {
			mutex.unlock();
		}
	}

	
}
