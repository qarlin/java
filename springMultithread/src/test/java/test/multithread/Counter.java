package test.multithread;

public class Counter {
	private static ThreadLocal<Integer> counter = new ThreadLocal<Integer>();

	public Counter(){
		counter.set(0);
	}
	
	public int getCountInThread() {
		return counter.get();
	}
}
