package localthread;

public class TestBeanUtil {

	private static TestBean testBean;

	public void setTestBean(TestBean testBean) {
		TestBeanUtil.testBean = testBean;
	}

	public static TestBean getTestBean() {
		return testBean;
	}
}