import java.io.IOException;


public class TestThread implements Runnable{
	public void run() {
		for(int i=0;i<100;i++){
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
		
	}
	public static void main(String[] args) {
		TestThread test=new TestThread();
		Thread t=new Thread(test);
		t.setDaemon(true);
		t.start();
		System.out.println("isDaemon="+t.isDaemon());
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
