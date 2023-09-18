package trainings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.base.BaseTest;

public class TestJava extends BaseTest{

    static Logger Log = LoggerFactory.getLogger(TestJava.class);

    public static void main(String[] args) {
        
        String text1 = System.getProperty("user.dir");
        System.out.println(text1.substring(text1.lastIndexOf("\\") + 1));
        
        
    }


}
