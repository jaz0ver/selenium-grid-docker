package framework.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    static Logger Log = LoggerFactory.getLogger(BaseTest.class);
    public Capabilities cap;
    URL url;
    
    // public ThreadLocal<WebDriver> driver = new InheritableThreadLocal<WebDriver>();
    public WebDriver driver;

    @Parameters({"selenium"})
    @BeforeSuite
    public void beforeSuite(@Optional("webdriver")String seleniumType) {
        String methodName = getMethodName();
        Log.info(methodName);
        Log.info("Running Selenium " + seleniumType.toUpperCase());
    }
    
    @BeforeTest
    public void beforeTest() {
        String methodName = getMethodName();
        Log.info(methodName);
        
    }
    
    @BeforeClass
    public void beforeClass() {
        String methodName = getMethodName();
        Log.info(methodName);
        
    }

    @Parameters ({"browser", "selenium"})
    @BeforeMethod
    public void beforeMethod(@Optional("chrome")String browser, @Optional("webdriver")String seleniumType) {
        String methodName = getMethodName();
        Log.info(methodName);
        Log.info(methodName + "Browser: " + browser);
        initDriver(browser, seleniumType);
        cap = ((RemoteWebDriver) driver).getCapabilities();
        Log.info("Device details: " + cap.getBrowserName().toUpperCase() + " " + cap.getBrowserVersion());
    }
    
    @AfterMethod
    public void afterMethod() {
        String methodName = getMethodName();
        Log.info(methodName);
        Log.info(methodName + "Tear down");
        driver.quit();
        
    }
    @AfterClass
    public void afterClass() {
        String methodName = getMethodName();
        Log.info(methodName);
        
    }
    
    @AfterTest
    public void afterTest() {
        String methodName = getMethodName();
        Log.info(methodName);
        
    }
    
    @AfterSuite
    public void afterSuite() {
        String methodName = getMethodName();
        Log.info(methodName);
    }

    public void initDriver(String browser, String seleniumType) {
        String methodName = getMethodName();
        DesiredCapabilities dc = new DesiredCapabilities();
        try {
            seleniumType = seleniumType.trim();
            if (seleniumType.equalsIgnoreCase("grid")) {
                url = new URL("http://127.0.0.1:4444/wd/hub");
                switch (browser) {
                    case "edge":
                        // EdgeOptions options = new EdgeOptions();
                        // options.addArguments("--ignore-certificate-errors");
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
                driver = new RemoteWebDriver(url, dc);
            } else if(seleniumType.equalsIgnoreCase("webDriver")) {
                switch (browser) {
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    default:
                        Log.warn(methodName + "No specific browser was set. " + browser);
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
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
    
    public static void runTerminalCommand(String command,String logText) {
        String methodName = getMethodName();
        Log.info(methodName);
		try {
			String path = System.getProperty("user.dir");
			ProcessBuilder builder = new ProcessBuilder(
					"cmd.exe", "/c", "cd \""+path+"\" && "+command);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
            int timeout = 1;
			while (true) {
                Thread.sleep(1000);
				line = r.readLine();
				if (line.contains(logText)) { 
					Log.info(line);
                    Log.info("Displayed after " + timeout + "seconds");
					break; 
				} else if(timeout == 300) { // 5mins timeout
                    Log.error(logText + "didn not display after " + timeout + "seconds");
                }
                timeout++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static String getMethodName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return "[" + methodName + "] ";
    }
}
