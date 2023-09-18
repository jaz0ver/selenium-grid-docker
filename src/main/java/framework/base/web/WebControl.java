package framework.base.web;

import framework.base.BaseTest;
import framework.base.DriverManager;
import framework.utilities.reporter.ExtentTestManager;

public class WebControl extends BaseTest{

    public static void goToURL(String url) {
        String methodDescription = "Go to url \"" + url + "\"";
        DriverManager.getDriver().get(url);
        ExtentTestManager.methodDescription = methodDescription;
        ExtentTestManager.passStep(methodDescription);
    }

}
