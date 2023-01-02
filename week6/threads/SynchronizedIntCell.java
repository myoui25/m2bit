package ss.week6.threads;

public class SynchronizedIntCell implements IntCell{
	private int value = 0;
	private boolean newData;

	@Override
	public synchronized void setValue(int val) {
		while (newData) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.value = val;
		newData = true;
		this.notifyAll();
	}

	@Override
	public synchronized int getValue() {
		while(!newData) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		newData = false;
		this.notifyAll();
		return value;
	}

}
