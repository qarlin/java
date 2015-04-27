package cid;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import cdi.CourseService;

@RunWith(WeldJUnit4Runner.class)
public class LogTest {

	@Inject
    private CourseService courseService;

    @Test
    public void testCDI() {
        courseService.registerCourse("Unit Testing CDI in a Java SE environment with JUnit and JBoss Weld");
    }
}
