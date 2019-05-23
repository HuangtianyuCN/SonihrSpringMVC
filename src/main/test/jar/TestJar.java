package jar;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 16:46
**/

import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class TestJar {
    @Test
    public void testPostBeanProcessor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testjar.xml");
        Driveable car = (Driveable) applicationContext.getBean("car");
        car.running();
    }
}
