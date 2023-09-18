package framework.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);;
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void unloadDriver() {
        threadLocalDriver.remove();
    }
}
