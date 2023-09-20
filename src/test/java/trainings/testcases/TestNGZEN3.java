package trainings.testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.base.BaseTest;
import framework.base.web.WebControl;
import framework.utilities.ConfigFileReader;
import framework.utilities.MyDataProvider;
import framework.utilities.reporter.ExtentTestManager;
import pageobject.projects.infor.LoginPage;

@Listeners(framework.utilities.listeners.TestListener.class)

public class TestNGZEN3 extends BaseTest {

	Logger Log = LoggerFactory.getLogger(TestNGZEN3.class);
	String author = "Jaz";

	@Test(groups = { "smoke", "regression" }, invocationCount = 1, priority = 1, dataProvider = "Excel_Data", dataProviderClass = MyDataProvider.class, description = "Testing login page of Infor test site")
	public void TC1_Infor_Login(String user, String pw) {
		Log.info("------------- Test Case 1 -------------");
		ExtentTestManager.startTest(getTestName(), "Infor valid test");
		ExtentTestManager.assignAuthor(author);
		WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
		LoginPage.header.brandImg.verifyIsVisible();
		LoginPage.login(user, pw);
		LoginPage.userTxtbox.verifyIsVisible();
	}

	@Test(dataProvider = "getDataByTcName", dataProviderClass = MyDataProvider.class)
	public void TC2_Infor_Login(String user, String pw) {
		Log.info("------------- Test Case 2 -------------");
		ExtentTestManager.startTest(getTestName(), "Infor valid test");
		ExtentTestManager.assignAuthor(author);
		WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
		ExtentTestManager.failStep("Sample Failed Test");
		LoginPage.login(user, pw);
		LoginPage.userTxtbox.verifyIsNotVisible();
	}
	
	@Test(dataProvider = "getDataByTcName", dataProviderClass = MyDataProvider.class)
	public void TC3_Infor_Login(String user, String pw) {
		Log.info("------------- Test Case 2 -------------");
		ExtentTestManager.startTest(getTestName(), "Infor valid test");
		ExtentTestManager.assignAuthor(author);
		WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
		LoginPage.login(user, pw);
		LoginPage.userTxtbox.verifyIsVisible();
	}
}
