package framework.base;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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

import framework.utilities.ConfigFileReader;
import framework.utilities.reporter.ExtentManager;

public class BaseTest {

    static Logger Log = LoggerFactory.getLogger(BaseTest.class);

    @Parameters({ "selenium" })
    @BeforeSuite
    public void beforeSuite(@Optional("webdriver") String seleniumType) {
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

    @Parameters({ "browser", "selenium" })
    @BeforeMethod
    public void beforeMethod(@Optional("config_browser") String browser, @Optional("webdriver") String seleniumType) {
        String methodName = getMethodName();
        Log.info(methodName);
        browser = getBrowser(browser);
        Log.info(methodName + "Browser: " + browser);
        Driver.initDriver(browser, seleniumType);
    }

    @Parameters({ "browser" })
    @AfterMethod
    public void afterMethod(@Optional("config_browser") String browser) {
        String methodName = getMethodName();
        Log.info(methodName);
        Driver.tearDown(getBrowser(browser));
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
        reportTearDown();
    }

    public static String getMethodName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return "[" + methodName + "] ";
    }

    public static String getTestName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return methodName;
    }

    public String getBrowser(String browser) {
        if (browser.equalsIgnoreCase("config_browser")) {
            browser = ConfigFileReader.getProperty("browser");
        }
        return browser;
    }

    public static void runTerminalCommand(String command, String logText) {
        String methodName = getMethodName();
        Log.info(methodName);
        try {
            String path = System.getProperty("user.dir");
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"" + path + "\" && " + command);
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
                } else if (timeout == 300) { // 5mins timeout
                    Log.error(logText + "didn not display after " + timeout + "seconds");
                }
                timeout++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reportTearDown() {
        String methodName = getMethodName();
        // Do tier down operations for ExtentReports reporting
        ExtentManager.extentReports.flush();
        boolean flagOpenReport = false;
        String extentReport = System.getProperty("open_extent_report");
        if (extentReport != null) { // check if open_extent_report parameter in command line
            if (extentReport.toLowerCase().equals("true")) {
                flagOpenReport = true;
            }
        } else if ((ConfigFileReader.getProperty("openReport")).toLowerCase().trim().equals("true")) {
            flagOpenReport = true;
        }
        if (flagOpenReport) {
            // Automatically open the report after execution
            String extentReportFilePath = ExtentManager.extentReportFilePath;
            try {
                Log.info(methodName + "Opening report. Path: " + extentReportFilePath);
                Desktop.getDesktop().browse(new File(extentReportFilePath).toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
