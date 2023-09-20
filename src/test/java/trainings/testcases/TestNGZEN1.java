package trainings.testcases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.base.DriverManager;
import framework.base.web.WebControl;
import framework.utilities.reporter.ExtentTestManager;

@Listeners(framework.utilities.listeners.TestListener.class)

public class TestNGZEN1 extends BaseTest{

    static Logger Log = LoggerFactory.getLogger(TestNGZEN1.class);
	String author = "Cirnez";
    
    @Test
    public void testSeleniumGrid1() {
		ExtentTestManager.startTest(getTestName(), "Sample test");
		ExtentTestManager.assignAuthor(author);
        Log.info(getMethodName() + "Some code here");
        WebControl.goToURL("https://www.google.com/");
        Log.info(DriverManager.getDriver().getTitle());
    }
    
    @Test
    public void testSeleniumGrid2() {
		ExtentTestManager.startTest(getTestName(), "Sample test");
		ExtentTestManager.assignAuthor(author);
        Log.info(getMethodName() + "Some code here");
        WebControl.goToURL("https://www.facebook.com/");
        Log.info(DriverManager.getDriver().getTitle());
    }
    
    @Test
    public void testSeleniumGrid3() {
		ExtentTestManager.startTest(getTestName(), "Sample test");
		ExtentTestManager.assignAuthor(author);
        Log.info(getMethodName() + "Some code here");
        WebControl.goToURL("https://en.wikipedia.org/wiki/Main_Page");
        Log.info(DriverManager.getDriver().getTitle());
    }
    
    @Test
    public void testSeleniumGrid4() {
		ExtentTestManager.startTest(getTestName(), "Sample test");
		ExtentTestManager.assignAuthor(author);
        Log.info(getMethodName() + "Some code here");
        WebControl.goToURL("https://www.tutorialspoint.com/about/about_careers.htm");
        Log.info(DriverManager.getDriver().getTitle());
    }

}
