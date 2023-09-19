package trainings.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.base.DriverManager;
import framework.base.web.WebControl;
import framework.utilities.ConfigFileReader;
import framework.utilities.reporter.ExtentTestManager;
import pageobject.projects.infor.HomePage;
import pageobject.projects.infor.LoginPage;
import pageobject.projects.infor.RegistrationPage;

@Listeners(framework.utilities.listeners.TestListener.class)

public class Infor_Exam extends BaseTest{

    String author = "Zenric Navea";
    String user = "infor_taas";
    String pass = "infor_taas";
    String firstName = "Zenric";
    String lastName = "Navea";
    String email = "jazx.zn@gmail.com";
    String phone = "09661401029";
    String gender = "Female";
    String companyName = "RCG Technology Inc.";
    String address1 = "Address Line 1";
    String address2 = "Address Line 2";
    String iFrame = "companyInfo";
    String skill1 = "Automation";
    String skill2 = "Selenium WebDriver";



    @Test
    public void infor_TC1() {
		ExtentTestManager.startTest(getTestName(), "Successful login to Infor Site");
		ExtentTestManager.assignAuthor(author);

        WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
        LoginPage.page.verifyPageTitle(20);
        LoginPage.page.verifyURL();
        LoginPage.userTxtbox.verifyEnter(user);
        LoginPage.passwordTxtbox.verifyEnter(pass);
        LoginPage.submitBtn.verifyClick();
        
        HomePage.page.verifyPageTitle(20);
    }

    @Test
    public void infor_TC2() {
		ExtentTestManager.startTest(getTestName(), "Successful registration to Infor Site");
		ExtentTestManager.assignAuthor(author);

        WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
        LoginPage.page.verifyPageTitle(20);
        LoginPage.page.verifyURL();
        LoginPage.login(user, pass);

        HomePage.page.verifyPageTitle(20);
        HomePage.page.verifyURL();
        HomePage.options.registrationBtn.verifyClick();

        RegistrationPage.stepOne.page.verifyPageTitle(20);
        RegistrationPage.stepOne.page.verifyURL();
        RegistrationPage.stepOne.firstNameTxtbox.verifyEnter(firstName);
        RegistrationPage.stepOne.lastNameTxtbox.verifyEnter(lastName);
        RegistrationPage.nextBtn.verifyClick();

        RegistrationPage.stepTwo.page.verifyPageTitle(20);
        RegistrationPage.stepTwo.page.verifyURL();
        RegistrationPage.stepTwo.emailTxtbox.verifyEnter(email);
        RegistrationPage.stepTwo.phoneTxtbox.verifyEnter(phone);
        RegistrationPage.stepTwo.genderDropdown.verifySelectByVisibleText(gender);

        WebControl.switchToiFrame("companyInfo");
        RegistrationPage.stepTwo.companyNameTxtbox.verifyEnter(companyName);
        RegistrationPage.stepTwo.address1Txtbox.verifyEnter(address1);
        RegistrationPage.stepTwo.address2Txtbox.verifyEnter(address2);
        WebControl.switchToParentFrame();
        RegistrationPage.nextBtn.verifyClickViaExecutor();

        RegistrationPage.stepThree.page.verifyPageTitle(20);
        RegistrationPage.stepThree.page.verifyURL();
        
        RegistrationPage.stepThree.skillOneTxtbox.verifyEnter(skill1);
        RegistrationPage.stepThree.addSkillTxtbox.verifyEnter("1");
        RegistrationPage.stepThree.addSkillBtn.verifyClick();
        RegistrationPage.stepThree.skillTwoTxtbox.verifyIsVisible();
        RegistrationPage.stepThree.skillTwoTxtbox.verifyEnter(skill2);
        RegistrationPage.nextBtn.verifyClick();

        RegistrationPage.stepFour.page.verifyPageTitle(20);
        RegistrationPage.stepFour.page.verifyURL();
    }
    
    @Test
    public void infor_TC3() {
		ExtentTestManager.startTest(getTestName(), "Succesful login to Infor Site");
		ExtentTestManager.assignAuthor(author);

        WebControl.goToURL(ConfigFileReader.getProperty("test_url"));
        LoginPage.page.verifyPageTitle(20);
        LoginPage.page.verifyURL();
        LoginPage.login(user, pass);

        HomePage.page.verifyPageTitle(20);
        HomePage.page.verifyURL();

        DriverManager.getDriver().findElement(By.xpath("//ul/li[" + ConfigFileReader.getProperty("explicit_timeout") + "]/a")).click();

        RegistrationPage.stepOne.page.verifyPageTitle(20);
        RegistrationPage.stepOne.page.verifyURL();
    }
}
