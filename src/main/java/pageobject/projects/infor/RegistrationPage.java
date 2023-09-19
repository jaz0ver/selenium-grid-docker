package pageobject.projects.infor;

import org.openqa.selenium.By;

import framework.base.web.Element;
import framework.base.web.Page;

public class RegistrationPage {
    
    // Page title and url/partial url 

    // Selector -> Registration page - Step 1
    public static Element nextBtn = new Element("Next button", By.cssSelector("a[href*=\"reg-step\"]"));
    public static StepOne stepOne = new StepOne("Registration page - Step 1", By.xpath("//h3[contains(text(), \"Step 1\")]/parent::div"));
    public static class StepOne extends Element{
        public StepOne(String description, By by) {
            super(description, by);
        }
        public Page page = new Page("Infor TaaS: Registration Step 1", "/reg-step1.html");
        public Element firstNameTxtbox = new Element("First Name field", By.cssSelector("input#firstName"), this);
        public Element lastNameTxtbox = new Element("Last Name field", By.cssSelector("input#lastName"), this);
    }
    
    // Selector -> Registration page - Step 2
    public static StepTwo stepTwo = new StepTwo("Registration page - Step 2", By.xpath("//h3[contains(text(), \"Step 2\")]/parent::div"));
    public static class StepTwo extends Element{
        public StepTwo(String description, By by) {
            super(description, by);
        }
        public Page page = new Page("nfor TaaS Demo: Registration Step 2", "/reg-step2.html");
        public Element emailTxtbox = new Element("Email field", By.cssSelector("input#email"), this);
        public Element phoneTxtbox = new Element("Phone field", By.cssSelector("input#phone"), this);
        public Element genderDropdown = new Element("Gender dropdown", By.cssSelector("select#gender"), this);
        public Element companyNameTxtbox = new Element("Company Name field", By.cssSelector("input#companyName"));
        public Element address1Txtbox = new Element("Address Line 1 field", By.cssSelector("input#address1"));
        public Element address2Txtbox = new Element("Address Line 2 field", By.cssSelector("input#address2"));
    }
    
    // Selector -> Registration page - Step 3
    public static StepThree stepThree = new StepThree("Registration page - Step 3", By.xpath("//h3[contains(text(), \"Step 3\")]/parent::div"));
    public static class StepThree extends Element{
        public StepThree(String description, By by) {
            super(description, by);
        }
        public Page page = new Page("Infor TaaS Demo: Registration Step 3", "/reg-step3.html");
        public Element attachBtn = new Element("Attachment button", By.cssSelector("button#popupBtn"), this);
        public Element addSkillTxtbox = new Element("Add skill field", By.cssSelector("input#count"), this);
        public Element addSkillBtn = new Element("Add skill button", By.cssSelector("button#add"), this);
        public Element skillOneTxtbox = new Element("Skill one field", By.cssSelector("input[name=\"skill_1\"]"));
        public Element skillTwoTxtbox = new Element("Skill two field", By.cssSelector("input[name=\"skill_2\"]"));
    }
    
    // Selector -> Registration page - Step 4
    public static StepFour stepFour = new StepFour("Registration page - Step 4", By.cssSelector("table#tblSchedule"));
    public static class StepFour extends Element{
        public StepFour(String description, By by) {
            super(description, by);
        }
        public Page page = new Page("Infor TaaS Demo: Registration Step 4", "/reg-step4.html");
        
    }
}
