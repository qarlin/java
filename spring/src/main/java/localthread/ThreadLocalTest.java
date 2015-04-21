package localthread;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ThreadLocalTest {
	//private ClassPathXmlApplicationContext context;
	private AnnotationConfigApplicationContext context;

	public static void main(String[] args) {
		new ThreadLocalTest();
	}

	public ThreadLocalTest() {

		//context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		for (int i = 0; i < 10; i++) {
			ThreadTest test = new ThreadTest(i);
			test.setName("Thread1-" + i);
			test.start();
		}

		for (int i = 0; i < 10; i++) {
			Thread2Test test2 = new Thread2Test(i);
			test2.setName("Thread2-" + i);
			test2.start();
		}
	}

	private class ThreadTest extends Thread {
		private final int number;

		public ThreadTest(int number) {
			this.number = number;
		}

		public void run() {
			TestBean testBean = (TestBean) context.getBean("testBean");

			testBean.setMyValue(String.valueOf(number));
			System.out.println(Thread.currentThread().getName()
					+ ", bean value: " + testBean.getMyValue());
		}
	}

	private class Thread2Test extends Thread {
		private final int number;

		public Thread2Test(int number) {
			this.number = number;
		}

		public void run() {
			TestBeanUtil.getTestBean().setMyValue(String.valueOf(number));
			System.out.println(Thread.currentThread().getName()
					+ ", bean value: "
					+ TestBeanUtil.getTestBean().getMyValue());
		}
	}

}