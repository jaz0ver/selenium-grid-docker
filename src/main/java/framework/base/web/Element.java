package framework.base.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.base.BaseTest;
import framework.base.DriverManager;
import framework.utilities.reporter.ExtentTestManager;

public class Element extends BaseTest {
    
    Logger Log = LoggerFactory.getLogger(Element.class);
    protected String description;
    protected By by;
	protected Element parent;
    
	public Element(String description, By by, Element parent){
		this.description = description;
		this.by = by;
		this.parent = parent;
	}

    public Element(String description, By by) {
        this.description = description;
        this.by = by;
    }

    /********************************
     ****** Web Element Actions *****
     ********************************/

    public WebElement findElement() {
        if (parent != null) {
            return parent.findElement().findElement(this.by);
        } else {
            return DriverManager.getDriver().findElement(this.by);
        }
    }
    
    public List<WebElement> findElements() {
        if (parent != null) {
            return parent.findElement().findElements(this.by);
        } else {
            return DriverManager.getDriver().findElements(this.by);
        }
    }

    public WebElement findParentElement() {
        return parent.findElement();
    }
    
    public boolean isExisting() {
        String methodDescription = getMethodName();
        boolean flag = findElements().size()!=0;
        if (flag) {
            Log.debug(methodDescription + "\"{}\" is existing.", description);
        } else {
            Log.debug(methodDescription + "\"{}\" was not existing.", description);
        }
        return flag;
    }

    public WebElement waitElementIsVisible() {
        waitElementIsExisting();
        DriverManager.getWait().until(d -> findElement().isDisplayed());
        return findElement();
    }

    public WebElement waitElementIsVisible(int timeout) {
        waitElementIsExisting(timeout);
        DriverManager.getWait(timeout).until(d -> findElement().isDisplayed());
        return findElement();
    }

    public void waitElementIsNotVisible() {
        if (isExisting()) {
            DriverManager.getWait().until(d -> findElement().isDisplayed()==false);
        }
    }
    
    public void waitElementIsNotVisible(int timeout) {
        if (isExisting()) {
            DriverManager.getWait(timeout).until(d -> findElement().isDisplayed()==false);    
        }
    }
    
    public WebElement waitElementIsExisting() {
        DriverManager.getWait().until(d -> isExisting());
        return findElement();
    }

    public WebElement waitElementIsExisting(int timeout) {
        DriverManager.getWait(timeout).until(d -> isExisting());
        return findElement();
    }
    
    public void waitElementIsNotExisting() {
        DriverManager.getWait().until(d -> isExisting()==false);
    }
    
    public void waitElementIsNotExisting(int timeout) {
        DriverManager.getWait(timeout).until(d -> isExisting()==false);
    }

    public boolean isDisplayed() {
        Log.info("\"{}\" is displayed.", description);
        return findElement().isDisplayed();
    }

    public void click() {
        Log.info("\"{}\" is clicked.", description);
        // findElement().click();
        waitElementIsVisible().click();
    }

    public void enter(String value) {
        String encryptedValue;
        if (description.toLowerCase().contains("password")) {
            encryptedValue = "*********";
        } else {
            encryptedValue = value;   
        }
        Log.info("\"{}\" is entered to \"{}\".", encryptedValue, description);
        // findElement().sendKeys(value);
        waitElementIsVisible().sendKeys(value);
    }

    /*************************************
     ****** Verification - Assertion *****
     *************************************/
    
    public void verifyIsVisible() {
        String methodDescription = "Verify \"" + description + "\" is visible.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        waitElementIsVisible();
        ExtentTestManager.passStep();
    }

    public void verifyIsVisible(int timeout) {
        String methodDescription = "Verify \"" + description + "\" is visible.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        if (timeout > 0) {
            Log.info("Timeout: {} seconds", timeout );
        }
        waitElementIsVisible(timeout);
        ExtentTestManager.passStep();
    }

    public void verifyIsNotVisible() {
        String methodDescription = "Verify \"" + description + "\" is not visible.";
        Log.info(methodDescription);
        ExtentTestManager.methodDescription = methodDescription;
        waitElementIsNotVisible();
        ExtentTestManager.passStep();
    }
    
    public void verifyIsNotVisible(int timeout) {
        String methodDescription = "Verify \"" + description + "\" is not visible.";
        Log.info(methodDescription);
        ExtentTestManager.methodDescription = methodDescription;
        if (timeout > 0) {
            Log.info("Timeout: {} seconds", timeout );
        }
        waitElementIsNotVisible(timeout);
        ExtentTestManager.passStep();
    }

    public void verifyIsExisting() {
        String methodDescription = "Verify \"" + description + "\" is existing.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        waitElementIsExisting();
        ExtentTestManager.passStep();
    }
    
    public void verifyIsExisting(int timeout) {
        String methodDescription = "Verify \"" + description + "\" is existing.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        if (timeout > 0) {
            Log.info("Timeout: {} seconds", timeout );
        }
        waitElementIsExisting(timeout);
        ExtentTestManager.passStep();
    }
    
    public void verifyIsNotExisting() {
        String methodDescription = "Verify \"" + description + "\" is not existing.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        waitElementIsNotExisting();
        ExtentTestManager.passStep();
    }
    
    public void verifyIsNotExisting(int timeout) {
        String methodDescription = "Verify \"" + description + "\" is not existing.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        if (timeout > 0) {
            Log.info("Timeout: {} seconds", timeout );
        }
        waitElementIsNotExisting(timeout);
        ExtentTestManager.passStep();
    }

}
