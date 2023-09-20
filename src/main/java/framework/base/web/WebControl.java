package framework.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.base.BaseTest;
import framework.base.DriverManager;
import framework.utilities.reporter.ExtentTestManager;

public class WebControl extends BaseTest{

    static Logger Log = LoggerFactory.getLogger(WebControl.class);

    public static void goToURL(String url) {
        String methodDescription = "Go to url \"" + url + "\"";
        DriverManager.getDriver().get(url);
        ExtentTestManager.passStep(methodDescription);
    }
    
    public static String getPageTitle() {
        String pageTitle = DriverManager.getDriver().getTitle();
        Log.info(getMethodName() + "Page title: " + pageTitle);
        return pageTitle;
    }
    
    public static void switchToiFrame(String idORname) {
        DriverManager.getDriver().switchTo().frame(idORname);
        Log.info(getMethodName() + "Switch to iframe, " + idORname);
    }
    
    public static void switchToiFrame(int index) {
        DriverManager.getDriver().switchTo().frame(index);
        Log.info(getMethodName() + "Switch to iframe by index " + index);
    }

    public static void switchToParentFrame() {
        // DriverManager.getDriver().switchTo().parentFrame();
        DriverManager.getDriver().switchTo().defaultContent();
        Log.info(getMethodName() + "Switch to parent frame");
    }
    
    /**************************
    ****** Verification *******
    **************************/

    public static void verifyPageTitle(String expectedPageTitle) {
        String methodDescription = "Verify page title is equals to \"" + expectedPageTitle + "\"";
        ExtentTestManager.setMethodDesc(methodDescription);
        Log.info(methodDescription);
        DriverManager.getWait().until(d -> expectedPageTitle.equals(DriverManager.getDriver().getTitle()));
        Log.info("Actual page title: " + DriverManager.getDriver().getTitle());
        ExtentTestManager.passStep();
    }
    
    public static void verifyPageTitle(String expectedPageTitle, int timeout) {
        String methodDescription = "Verify page title is equals to \"" + expectedPageTitle + "\"";
        ExtentTestManager.setMethodDesc(methodDescription);
        Log.info(methodDescription);
        if (timeout > 0) {
            Log.info("Timeout: {} seconds", timeout );
        }
        DriverManager.getWait(timeout).until(d -> expectedPageTitle.equals(DriverManager.getDriver().getTitle()));
        Log.info("Actual page title: " + DriverManager.getDriver().getTitle());
        ExtentTestManager.passStep();
    }
    

}
