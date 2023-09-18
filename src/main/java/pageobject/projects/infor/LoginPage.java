package pageobject.projects.infor;

import org.openqa.selenium.By;

// import framework.base.BaseTest;
import framework.base.web.Element;
import framework.utilities.reporter.ExtentTestManager;

public class LoginPage {

    // Selector - Login page
    public static Element userTxtbox = new Element("Username field", By.id("username"));
    public static Element passwordTxtbox = new Element("Password field", By.id("password"));
    public static Element submitBtn = new Element("Submit button", By.id("submit"));
    public static Element regBtn = new Element("Registration button", By.xpath("//li[text()='Registration Form']/a"));

    // Selector - Login page > Header
    public static final Header header = new Header("Header section", By.cssSelector(".page-header"));

    public static class Header extends Element {
        public Header(String description, By by) {
            super(description, by);
        }
        public Element brandImg = new Element("Infor logo", By.cssSelector(".page-header img"));
        public Element brandLabel = new Element("Infor site title", By.cssSelector(".page-header small"));
        public Element quote = new Element("Infor site description", By.cssSelector("blockquote small cite"));
    }

    // Funtion - Login page
    public static void login(String user, String pw) { 
        userTxtbox.enter(user);
        passwordTxtbox.enter(pw);
        submitBtn.click();
        ExtentTestManager.passStep("I enter user, \"" + user + "\" and password, \"" + pw + "\"");
    }

}
