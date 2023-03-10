package ss.week6.account;

public class MyThread extends Thread implements Runnable{
	private double theAmount;
	private int theFrequency;
	private Account theAccount;
	
	public MyThread(double amount, int frequency, Account account) {
		this.theAmount = amount;
		this.theFrequency = frequency;
		this.theAccount = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < theFrequency; i++) {
			theAccount.transaction(theAmount);
		}
	}

}
