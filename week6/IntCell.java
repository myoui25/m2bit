package ss.week6;

public class IntCell {
    private int contents = 0;

    public synchronized void add(int amount) {
        contents = contents + amount;
    }
    public synchronized int get() {
        return contents;
    }

    public static void main(String[] args) {
    	
    	IntCell cell = new IntCell();
    	Thread a1 = new Thread(new Adder(cell, 1));
        Thread a2 = new Thread(new Adder(cell, 2));
    	a1.start();
        a2.start();       
//      a1.run();
//      a2.run();
        try {
            a1.join();
            a2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cell.get());
    }
}

class Adder implements Runnable {
    private IntCell cell;
    private int amount;

    public Adder(IntCell cellArg, int amountArg) {
        this.cell = cellArg;
        this.amount = amountArg;
    }
    public void run() {
    	synchronized (cell) {
    		cell.add(amount);
		}
    }
}
