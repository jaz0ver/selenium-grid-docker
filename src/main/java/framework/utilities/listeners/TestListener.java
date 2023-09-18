package framework.utilities.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.Status;

import framework.base.BaseTest;
import framework.utilities.reporter.ExtentTestManager;

public class TestListener extends BaseTest implements ITestListener{

    static Logger Log = LoggerFactory.getLogger(TestListener.class);
    boolean screenRecoderFlag = false;

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("[onStart] "+iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("[onTestStart] " + getTestMethodName(iTestResult) + " test is starting.");
        getTestClassName(iTestResult);

    }

    // @Override
    // public void onTestSuccess(ITestResult iTestResult) {
    //     Log.info("[onTestSuccess] " + getTestMethodName(iTestResult) + " test is succeed.");
    //     ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    // }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info("[onTestFailure] " + getTestMethodName(iTestResult) + "Test fails unexpectedly.");
        String methodDescription = ExtentTestManager.methodDescription;
        if(methodDescription == null || methodDescription == "") {
            ExtentTestManager.failStep("Test fails unexpectedly.");
        } else {
            ExtentTestManager.failStep();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info("[onTestSkipped] " + getTestMethodName(iTestResult) + " test is skipped.");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }
    
    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("[onFinish]");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("[onTestFailedButWithinSuccessPercentage] Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    protected static String getTestMethodName(ITestResult iTestResult) {
        String name = iTestResult.getMethod().getConstructorOrMethod().getName();
        System.setProperty("currentMethodName", name);
        return name;
    }
    
    protected static String getTestClassName(ITestResult iTestResult) {
        String name = iTestResult.getMethod().getRealClass().getSimpleName();
        System.setProperty("currentClassName", name);
        return name;
    }
}
