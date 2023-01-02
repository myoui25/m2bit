package ss.week6.account;

public class Account {
	protected double balance = 0.0;

	public synchronized void transaction(double amount) {
		double value = balance + amount;
			try {
				if (value < -1000) {
					this.wait();
				}
				balance = balance + amount;
				this.notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	public double getBalance() {
		return balance;

	}
}
