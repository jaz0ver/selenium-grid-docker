package framework.base;

import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import framework.utilities.ConfigFileReader;

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

    public static Wait<WebDriver> getWait() {
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(Integer.parseInt(ConfigFileReader.getProperty("explicit_timeout"))))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static Wait<WebDriver> getWait(int timeout) {
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static Capabilities getCapabilities() {
            return ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
    }
}
