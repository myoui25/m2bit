package ss.week6.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FinegrainedIntCell implements IntCell{
	
	private final Lock lock = new ReentrantLock();
	private final Condition notRead = lock.newCondition();
	private final Condition notWritten = lock.newCondition();
	private int value = 0;
	private boolean newData;

	@Override
	public void setValue(int val) {
		lock.lock();
		try {
			while (newData) {
				notWritten.await();
			}
			this.value = val;
			newData = true;
			notRead.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
	}

	@Override
	public int getValue() {
		lock.lock();
		try {
			while (!newData) {
				try {
					notRead.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
			    }
			}
			newData = false;
			notWritten.signal();
			return value;
		} finally {
			lock.unlock();
		}
		
	}

}
