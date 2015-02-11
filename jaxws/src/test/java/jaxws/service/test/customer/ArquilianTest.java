package jaxws.service.test.customer;

import javax.ejb.EJB;

import jaxws.service.UserServices;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;


public class ArquilianTest {

	@EJB
    UserServices dummyService;
 
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserServices.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
 
    @Test
    public void testSaysHello() {
        Assert.assertEquals("hello",dummyService.sayHello());
        //Assert.assertNull(dummyService);
        System.out.println("Hola " + dummyService.sayHello());
    }

}
