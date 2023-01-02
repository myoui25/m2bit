package ss.week6.threads;

public class TestSyncConsole implements Runnable{
	
	public void run() {
		sum();
	}
	
	private synchronized void sum() {
		int number1 = SyncConsole.readInt(Thread.currentThread().getName() + ": get number 1? \n");
		int number2 = SyncConsole.readInt(Thread.currentThread().getName() + ": get number 2? \n");
		
		SyncConsole.println(Thread.currentThread().getName() + ": " + number1 + " + " + number2 + " = " + (number1 + number2));	
	}
	
	public static void main(String[] args) {
		TestSyncConsole syncConsole = new TestSyncConsole();
		
		new Thread(syncConsole, "Thread A").start();
		new Thread(syncConsole, "Thread B").start();
		
//		new Thread(new TestSyncConsole(), "Thread A").start();
//		new Thread(new TestSyncConsole(), "Thread B").start();
	}
	
}
