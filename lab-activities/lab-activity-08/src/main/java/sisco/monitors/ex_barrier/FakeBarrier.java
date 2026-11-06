package sisco.monitors.ex_barrier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Barrier - to be implemented
 */
public class FakeBarrier implements Barrier {

	private int nParticipants;
	private int nArrived;
	private Lock mutex;
	private Condition isFullCondition;

	public FakeBarrier(int nParticipants) {
		this.nParticipants = nParticipants;
		this.nArrived = 0;
		this.mutex = new ReentrantLock();
		this.isFullCondition = this.mutex.newCondition();
	}
	
	@Override
	public void hitAndWaitAll() throws InterruptedException {
		try {
			mutex.lock();
			nArrived++;
			while (!isFull()) {
				isFullCondition.await();
			}
			isFullCondition.signalAll();
		} finally {
			mutex.unlock();
		}
	}

	private boolean isFull() {
		return nArrived == nParticipants;
	}

}
