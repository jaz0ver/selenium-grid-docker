package trainings.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.base.DriverManager;
import framework.base.web.WebControl;
import framework.utilities.ConfigFileReader;
import framework.utilities.ExcelReader;
import framework.utilities.reporter.ExtentTestManager;
import pageobject.projects.infor.HomePage;
import pageobject.projects.infor.LoginPage;
import pageobject.projects.infor.RegistrationPage;

/***
* @Scenario: Infor Exam
* @Description: 
*
* @Scrum_Team: Alpha
* @Release: Build 2309
* @author Zenric Navea
* @Date_Created: 09/20/2023
*/

@Listeners(framework.utilities.listeners.TestListener.class)

public class Infor_Exam extends BaseTest{

    String author = "Zenric";
    ExcelReader testdata = new ExcelReader("testdata", "testcases");

    @Test
    public void TestScenario() {
        infor_TC1_Login(1);
        infor_TC2_Registration();
        infor_TC3_LoginViaConfigFile();
    }

    @Parameters({"count"})
    @Test(enabled=true)
    public void MultipleTCRunner(@Optional("1")String count) {
        for (int i = 0; i < Integer.parseInt(count); i++) {
            infor_TC1_Login(i);
        }
    }

    @Test(invocationCount = 1)
    public void infor_TC1_Login(@Optional("1")int count) {
		ExtentTestManager.startTest(getTestName() + "_" + count, "Successful login to Infor Site");
		ExtentTestManager.assignAuthor(author);

        WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
        LoginPage.page.verifyPageTitle(20);
        LoginPage.page.verifyURL();
        LoginPage.userTxtbox.verifyEnter(testdata.getData(getTestName(), "username"));
        LoginPage.passwordTxtbox.verifyEnter(testdata.getData(getTestName(), "password"));
        LoginPage.submitBtn.verifyClick();
        
        HomePage.page.verifyPageTitle(20);
    }

    @Test
    public void infor_TC2_Registration() {
		ExtentTestManager.startTest(getTestName(), "Successful registration to Infor Site");
		ExtentTestManager.assignAuthor(author);

        WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
        LoginPage.page.verifyPageTitle(20);
        LoginPage.page.verifyURL();
        LoginPage.login(testdata.getData(getTestName(), "username"), testdata.getData(getTestName(), "password"));

        HomePage.page.verifyPageTitle(20);
        HomePage.page.verifyURL();
        HomePage.options.registrationBtn.verifyClick();

        RegistrationPage.stepOne.page.verifyPageTitle(20);
        RegistrationPage.stepOne.page.verifyURL();
        RegistrationPage.stepOne.firstNameTxtbox.verifyEnter(testdata.getData(getTestName(), "firstname"));
        RegistrationPage.stepOne.lastNameTxtbox.verifyEnter(testdata.getData(getTestName(), "lastname"));
        RegistrationPage.nextBtn.verifyClick();

        RegistrationPage.stepTwo.page.verifyPageTitle(20);
        RegistrationPage.stepTwo.page.verifyURL();
        RegistrationPage.stepTwo.emailTxtbox.verifyEnter(testdata.getData(getTestName(), "email"));
        RegistrationPage.stepTwo.phoneTxtbox.verifyEnter(testdata.getData(getTestName(), "phone"));
        RegistrationPage.stepTwo.genderDropdown.verifySelectByVisibleText(testdata.getData(getTestName(), "gender"));

        WebControl.switchToiFrame("companyInfo");
        RegistrationPage.stepTwo.companyNameTxtbox.verifyEnter(testdata.getData(getTestName(), "companyname"));
        RegistrationPage.stepTwo.address1Txtbox.verifyEnter(testdata.getData(getTestName(), "address1"));
        RegistrationPage.stepTwo.address2Txtbox.verifyEnter(testdata.getData(getTestName(), "address2"));
        WebControl.switchToParentFrame();
        RegistrationPage.nextBtn.verifyClickViaExecutor();

        RegistrationPage.stepThree.page.verifyPageTitle(20);
        RegistrationPage.stepThree.page.verifyURL();
        RegistrationPage.stepThree.skillOneTxtbox.verifyEnter(testdata.getData(getTestName(), "skill1"));
        RegistrationPage.stepThree.addSkillTxtbox.verifyEnter("1");
        RegistrationPage.stepThree.addSkillBtn.verifyClick();
        RegistrationPage.stepThree.skillTwoTxtbox.verifyIsVisible();
        RegistrationPage.stepThree.skillTwoTxtbox.verifyEnter(testdata.getData(getTestName(), "skill2"));
        RegistrationPage.nextBtn.verifyClick();

        RegistrationPage.stepFour.page.verifyPageTitle(20);
        RegistrationPage.stepFour.page.verifyURL();
    }
    
    @Test
    public void infor_TC3_LoginViaConfigFile() {
		ExtentTestManager.startTest(getTestName(), "Succesful login to Infor Site");
		ExtentTestManager.assignAuthor(author);

        WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
        LoginPage.page.verifyPageTitle(20);
        LoginPage.page.verifyURL();
        LoginPage.login(testdata.getData(getTestName(), "username"), testdata.getData(getTestName(), "password"));

        HomePage.page.verifyPageTitle(20);
        HomePage.page.verifyURL();

        DriverManager.getDriver().findElement(By.xpath("//ul/li[" + ConfigFileReader.getProperty("POM_Test") + "]/a")).click();

        RegistrationPage.stepOne.page.verifyPageTitle(20);
        RegistrationPage.stepOne.page.verifyURL();
    }
}
