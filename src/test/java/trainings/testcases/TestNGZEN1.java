package trainings.testcases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import framework.base.BaseTest;

public class TestNGZEN1 extends BaseTest{

    static Logger Log = LoggerFactory.getLogger(TestNGZEN1.class);
    
    @Test
    public void testSeleniumGrid1() {
        Log.info(getMethodName() + "Some code here");
        driver.get("https://www.google.com/");
        Log.info(driver.getTitle());
    }
    
    @Test
    public void testSeleniumGrid2() {
        Log.info(getMethodName() + "Some code here");
        driver.get("https://www.facebook.com/");
        Log.info(driver.getTitle());
    }
    
    @Test
    public void testSeleniumGrid3() {
        Log.info(getMethodName() + "Some code here");
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        Log.info(driver.getTitle());
    }
    
    @Test
    public void testSeleniumGrid4() {
        Log.info(getMethodName() + "Some code here");
        driver.get("https://www.youtube.com/");
        Log.info(driver.getTitle());
    }
}
