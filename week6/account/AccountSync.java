package ss.week6.account;

public class AccountSync {
	public static void main(String[] args) {
		Account account = new Account();
		Thread accountIncrease = new MyThread(10, 5, account);
		Thread accountDecrease = new MyThread(-10, 5, account);
		
		accountIncrease.start();
		accountDecrease.start();
		
		try {
			accountIncrease.join();
			accountDecrease.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Balance: " + account.getBalance());
	}

}
