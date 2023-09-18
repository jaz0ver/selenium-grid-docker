package framework.base.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.base.BaseTest;
import framework.base.DriverManager;
import framework.utilities.reporter.ExtentTestManager;

public class Element extends BaseTest {
    
    Logger Log = LoggerFactory.getLogger(Element.class);
    protected String description;
    protected By by;

    public Element(String description, By by) {
        this.description = description;
        this.by = by;
    }

    /********************************
     ****** Web Element Actions *****
     ********************************/

    public WebElement findElement() {
        Assert.assertTrue(isExisting(), "Verify \"" + description + "\" is existing.");
        return DriverManager.getDriver().findElement(this.by);
    }
    
    public List<WebElement> findElements() {
        return DriverManager.getDriver().findElements(this.by);
    }
    
    public boolean isExisting() {
        boolean flag = findElements().size()!=0;
        if (flag) {
            Log.info("\"" + description + "\" is existing.");
        } else {
            Log.info("\"" + description + "\" was not existing.");
        }
        return flag;
    }

    public WebElement waitElementIsVisible() {
        return DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(this.by));
    }

    public Boolean waitElementIsNotVisible() {
        return DriverManager.getWait().until(ExpectedConditions.invisibilityOfElementLocated(this.by));
    }

    public boolean isDisplayed() {
        Log.info("\"" + description + "\" is displayed.");
        return findElement().isDisplayed();
    }

    public void click() {
        Log.info("\"" + description + "\" is clicked.");
        findElement().click();
    }

    public void enter(String value) {
        String encryptedValue;
        if (description.toLowerCase().contains("password")) {
            encryptedValue = "*********";
        } else {
            encryptedValue = value;   
        }
        Log.info("\"" + encryptedValue + "\" is entered to \"" + description + "\".");
        findElement().sendKeys(value);
    }

    /*************************************
     ****** Verification - Assertion *****
     *************************************/

    public void verifyIsVisible() {
        String methodDescription = "Verify \"" + description + "\" is not visible.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        Assert.assertTrue(findElement().isDisplayed(), methodDescription);
        ExtentTestManager.passStep();
    }

    public void verifyIsNotVisible() {
        String methodDescription = "Verify \"" + description + "\" is not visible.";
        Log.info(methodDescription);
        ExtentTestManager.methodDescription = methodDescription;
        Boolean flag = isExisting();
        if (flag) {
            flag = findElement().isDisplayed();
            Assert.assertFalse(flag, methodDescription);
        }
        ExtentTestManager.passStep();
    }
    
    public void verifyIsExisting() {
        String methodDescription = "Verify \"" + description + "\" is existing.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        Assert.assertTrue(isExisting(), methodDescription);
        ExtentTestManager.passStep();
    }
    
    public void verifyIsNotExisting() {
        String methodDescription = "Verify \"" + description + "\" is not existing.";
        ExtentTestManager.methodDescription = methodDescription;
        Log.info(methodDescription);
        Assert.assertFalse(isExisting(), methodDescription);
        ExtentTestManager.passStep();
    }

}
