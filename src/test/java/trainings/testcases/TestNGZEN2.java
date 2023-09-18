package trainings.testcases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import framework.WebControl;
import framework.base.BaseTest;
import framework.base.DriverManager;
import framework.utilities.reporter.ExtentTestManager;

@Listeners(framework.utilities.listeners.TestListener.class)

public class TestNGZEN2 extends BaseTest{

    static Logger Log = LoggerFactory.getLogger(TestNGZEN1.class);
    
    @Test
    public void testSeleniumGrid1_2() {
		ExtentTestManager.startTest(getTestName(), "Sample test");
        Log.info(getMethodName() + "Some code here");
        WebControl.goToURL("https://www.google.com/");
        Log.info(DriverManager.getDriver().getTitle());
    }
    
    @Test
    public void testSeleniumGrid2_2() {
		ExtentTestManager.startTest(getTestName(), "Sample test");
        Log.info(getMethodName() + "Some code here");
        WebControl.goToURL("https://www.facebook.com/");
        Log.info(DriverManager.getDriver().getTitle());
    }
    
    @Test
    public void testSeleniumGrid3_2() {
		ExtentTestManager.startTest(getTestName(), "Sample test");
        Log.info(getMethodName() + "Some code here");
        WebControl.goToURL("https://en.wikipedia.org/wiki/Main_Page");
        Log.info(DriverManager.getDriver().getTitle());
    }
    
    @Test
    public void testSeleniumGrid4_2() {
		ExtentTestManager.startTest(getTestName(), "Sample test");
        Log.info(getMethodName() + "Some code here");
        WebControl.goToURL("https://www.youtube.com/");
        Log.info(DriverManager.getDriver().getTitle());
    }
}