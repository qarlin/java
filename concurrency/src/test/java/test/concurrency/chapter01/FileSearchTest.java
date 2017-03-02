package test.concurrency.chapter01;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import concurrency.chapter01.FileSearch;

public class FileSearchTest {

	@Test
	public void test() {
		FileSearch searcher = new FileSearch("c:\\", "autoexec.bat");
		Thread thread = new Thread(searcher);
		thread.start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
