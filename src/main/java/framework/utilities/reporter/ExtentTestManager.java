package framework.utilities.reporter;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import framework.base.DriverManager;

/** extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling createExtentReports() method from ExtentManager.
 * At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
 * At getTest() method, return ExtentTest instance in extentTestMap by using current thread id. 
*/

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.createExtentReports();
    static Logger Log = LoggerFactory.getLogger(ExtentTestManager.class);
    public static String methodDescription = "";

    public static synchronized ExtentTest startTest(String testName, String desc) {
        Log.info("Test case name: " + testName);
        ExtentTest test = extent.createTest(testName, desc);
        Capabilities cap = DriverManager.getCapabilities();
        test.assignDevice(cap.getBrowserName().toUpperCase() + "_v" + cap.getBrowserVersion());
        test.assignCategory(System.getProperty("currentClassName") + ".class");
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static void addCategory(String category) {
        getTest().assignCategory(category);
    }
    
    public static void assignAuthor(String author) {
        getTest().assignAuthor(author);
    }

    public static void passStep(String methodDescription) {
        Log.info(methodDescription);
        getTest().log(Status.PASS, methodDescription);
    }

    public static void passStep() {
        checkMethodDesc();
        Log.info(methodDescription);
        getTest().log(Status.PASS, methodDescription);
        methodDescription = "";
    }

    public static void failStep(String methodDescription) {
        Log.error(methodDescription);
        getTest().log(Status.FAIL, methodDescription, getScreenshotAsBase64());

    }

    public static void failStep() {
        checkMethodDesc();
        Log.error(methodDescription);
        getTest().log(Status.FAIL, methodDescription, getScreenshotAsBase64());
        methodDescription = "";
    }

    public static void skipStep(String methodDescription) {
        Log.error(methodDescription);
        getTest().log(Status.SKIP, methodDescription);
    }

    public static void skipStep() {
        checkMethodDesc();
        Log.error(methodDescription);
        getTest().log(Status.SKIP, methodDescription);
        methodDescription = "";
    }

    public static Media getScreenshotAsBase64() {
        String base64Screenshot = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        return ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0);
    }

    public static void checkMethodDesc() {
        if (methodDescription == "") {
            getTest().log(Status.SKIP, "Please specify the method description. ExtentTestManager.methodDescription = methodDescription");
        }
    }
}
