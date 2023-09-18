package framework.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.utilities.functions.CommonFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    
    static Logger Log = LoggerFactory.getLogger(Driver.class);
    private static String gridURL = "http://127.0.0.1:4444/wd/hub";

    public static void initDriver(String browser, String seleniumType) {
        String methodName = CommonFunctions.getMethodName();
        DesiredCapabilities dc = new DesiredCapabilities();
        try {
            seleniumType = seleniumType.trim();
            if (seleniumType.equalsIgnoreCase("grid")) {
                URL url = new URL(gridURL);
                switch (browser) {
                    case "edge":
                        dc.acceptInsecureCerts();
                        dc.setBrowserName("MicrosoftEdge");
                        break;
                    case "firefox":
                        dc.setBrowserName("firefox");
                        break;
                    default:
                        Log.warn(methodName + "No specific browser was set. " + browser);
                    case "chrome":
                        dc.setBrowserName("chrome");
                        break;
                }
                DriverManager.setDriver(new RemoteWebDriver(url, dc));
            } else if(seleniumType.equalsIgnoreCase("webDriver")) {
                switch (browser) {
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        DriverManager.setDriver(new EdgeDriver());
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        DriverManager.setDriver(new FirefoxDriver());
                        break;
                    default:
                        Log.warn(methodName + "No specific browser was set. " + browser);
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        DriverManager.setDriver(new ChromeDriver());
                        break;
                }
            } else {
                Log.error(methodName + "Selenium type was not specified");
            }
        } catch (MalformedURLException e) {
            Log.error(e.toString());
            e.printStackTrace();
        }
    }

    public static void tearDown() {
        String methodName = CommonFunctions.getMethodName();
        Log.info(methodName + "Tear down");
        DriverManager.getDriver().quit();
        DriverManager.unloadDriver();
    }
}
