package framework.base.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import framework.base.DriverManager;

public class JSExecutor {
    
    public static void click(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }
}
