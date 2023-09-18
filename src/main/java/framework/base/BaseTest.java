package framework.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.openqa.selenium.Capabilities;
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


public class BaseTest {

    static Logger Log = LoggerFactory.getLogger(BaseTest.class);
    public Capabilities cap;

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
        Driver.initDriver(browser, seleniumType);
        cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        Log.info("Device details: " + cap.getBrowserName().toUpperCase() + " " + cap.getBrowserVersion());
    }

    @AfterMethod
    public void afterMethod() {
        String methodName = getMethodName();
        Log.info(methodName);
        Driver.tearDown();
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
