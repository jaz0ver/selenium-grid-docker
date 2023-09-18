package framework;

import framework.base.BaseTest;
import framework.base.DriverManager;

public class WebControl extends BaseTest{

    public static void goToURL(String url) {
        DriverManager.getDriver().get(url);
    }

}
