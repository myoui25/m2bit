package ss.week6.threads;

public class TestConsole implements Runnable {

	@Override
	public void run() {
		sum();
	}
	
	private void sum() {
		int number1 = Console.readInt(Thread.currentThread().getName() + ": get number 1? \n");
		int number2 = Console.readInt(Thread.currentThread().getName() + ": get number 2? \n");
		
		Console.println(Thread.currentThread().getName() + ": " + number1 + " + " + number2 + " = " + (number1 + number2));	
	}
	
	public static void main(String[] args) {
		new Thread(new TestConsole(), "Thread A").start();
		new Thread(new TestConsole(), "Thread B").start();
	}

}
