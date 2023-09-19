package framework.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.base.DriverManager;
import framework.utilities.reporter.ExtentTestManager;

public class Page {

    static Logger Log = LoggerFactory.getLogger(Page.class);
    protected String pageTitle;
    protected String url;

    public Page(String pageTitle, String url) {
        this.pageTitle = pageTitle;
        this.url = url;
    }
    
    /**************************
    ****** Verification *******
    **************************/

    public void verifyPageTitle() {
        String methodDescription = "Verify page title is equals to \"" + pageTitle + "\".";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        DriverManager.getWait().until(d -> pageTitle.equals(DriverManager.getDriver().getTitle()));
        Log.info("Actual page title: " + DriverManager.getDriver().getTitle());
        ExtentTestManager.passStep();
    }
    
    public void verifyPageTitle(int timeout) {
        String methodDescription = "Verify page title is equals to \"" + pageTitle + "\".";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        if (timeout > 0) {
            Log.info("Timeout: {} seconds", timeout );
        }
        DriverManager.getWait(timeout).until(d -> pageTitle.equals(DriverManager.getDriver().getTitle()));
        Log.info("Actual page title: " + DriverManager.getDriver().getTitle());
        ExtentTestManager.passStep();
    }
    
    public void verifyURL() {
        String methodDescription = "Verify is in correct url, \"" + url + "\".";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        DriverManager.getWait().until(d -> (DriverManager.getDriver().getCurrentUrl().contains(url)));
        Log.info("Actual URL: " + DriverManager.getDriver().getCurrentUrl());
        ExtentTestManager.passStep();
    }
    
}
