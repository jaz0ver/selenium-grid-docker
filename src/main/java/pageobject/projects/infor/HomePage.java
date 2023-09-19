package pageobject.projects.infor;

import org.openqa.selenium.By;

import framework.base.web.Element;
import framework.base.web.Page;

public class HomePage {

    // Page title and url/partial url
    public static Page page = new Page("Infor TaaS Demo: MainMenu", "/main-menu.html");

    // Selector - Home page > Body
    public static Options options = new Options("Main-menu section", By.cssSelector("ul.list-group"));
    public static class Options extends Element {
        public Options(String description, By by) {
            super(description, by);
        }
        public Element registrationBtn = new Element("Registation button", By.cssSelector("a[href=\"reg-step1.html\"]"), this);
        
    }
    
}
