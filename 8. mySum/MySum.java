package mySum;

public class MySum extends Thread{

	int sum = 0;
	Thread t;

	//1. no sync
		void increaseSum(Thread t) {
			try {
				Thread.sleep(100);
				System.out.println(t.getName() + " sum is: " + (++sum));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}

	 //2. method sync 
//		synchronized void increaseSum(Thread t) {
//			try {
//				Thread.sleep(100);
//				System.out.println(t.getName() + " sum is: " + (++sum));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}		
//		}

	 //3. block sync
//	void increaseSum(Thread t) {
//		try {
//			synchronized (this) {
//				Thread.sleep(100);
//				++sum;
//			}
//			System.out.println(t.getName() + " sum is: " + sum);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}		
//	}

	public static void main(String[] args) {
		MySum mysum = new MySum();
		for (int i = 0; i < 100; i++) {
			mysum.t = new Thread(mysum);
			mysum.t.start();
		}
	}

	@Override
	public void run() {
		increaseSum(t);
	}		
}