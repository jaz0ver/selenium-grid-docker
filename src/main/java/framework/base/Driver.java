package framework.base;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.utilities.ConfigFileReader;
import framework.utilities.functions.CommonFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    
    static Logger Log = LoggerFactory.getLogger(Driver.class);

    public static void initDriver(String browser, String seleniumType) {
        String methodName = CommonFunctions.getMethodName();
        DesiredCapabilities dc = new DesiredCapabilities();
        try {
            seleniumType = seleniumType.trim();
            if (seleniumType.equalsIgnoreCase("grid")) {
                URL url = new URL(ConfigFileReader.getProperty("grid_hub"));
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
                    case "chrome_debug":
                        WebDriverManager.chromedriver().setup();
                        DriverManager.setDriver(new ChromeDriver(chromeDebug()));
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
            Capabilities cap = DriverManager.getCapabilities();
            Log.info("Device details: " + cap.getBrowserName().toUpperCase() + " " + cap.getBrowserVersion());
            DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigFileReader.getProperty("explicit_timeout"))));

        } catch (MalformedURLException e) {
            Log.error(e.toString());
            e.printStackTrace();
        }
    }

    private static ChromeOptions chromeDebug() {
        String methodName = CommonFunctions.getMethodName();
        boolean existingSession = false;
        try {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost",9222));
            socket.close();
            existingSession = true;
        } catch (IOException e) {
            // e.printStackTrace();
        }
        ChromeOptions options = new ChromeOptions();
        if (existingSession) {
            options.setExperimentalOption("debuggerAddress", "localhost:9222");
            Log.info(methodName + "CHROME DEBUG MODE - Use existing session");
        } else {
            options.addArguments("--remote-debugging-port=9222");
            Log.info(methodName + "CHROME DEBUG MODE - Creating new session");
        }
        return options;
    }

    public static void tearDown(String browser) {        
        if (!browser.equalsIgnoreCase("chrome_debug")) {
            String methodName = CommonFunctions.getMethodName();
            Log.info(methodName + "Tear down");
            DriverManager.getDriver().quit();
            DriverManager.unloadDriver();
        }
    }
}
