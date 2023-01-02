package ss.week6.threads;

import java.util.concurrent.locks.ReentrantLock;

public class TestSyncConsoleLock {
	private final ReentrantLock lock = new ReentrantLock();
	
	public void run() {
		sum();
	}
	
	private synchronized void sum() {
		lock.lock();
		try {
			int number1 = SyncConsole.readInt(Thread.currentThread().getName() + ": get number 1? \n");
			int number2 = SyncConsole.readInt(Thread.currentThread().getName() + ": get number 2? \n");
			
			SyncConsole.println(Thread.currentThread().getName() + ": " + number1 + " + " + number2 + " = " + (number1 + number2));	
		} finally {
			lock.unlock();
		}
		
	}
	
	public static void main(String[] args) {
		TestSyncConsole syncConsole = new TestSyncConsole();
		
		new Thread(syncConsole, "Thread A").start();
		new Thread(syncConsole, "Thread B").start();
	}
	
}
