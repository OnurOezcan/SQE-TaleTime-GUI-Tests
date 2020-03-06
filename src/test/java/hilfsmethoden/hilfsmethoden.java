package hilfsmethoden;

import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;


public abstract class hilfsmethoden {

    private final static String URL = "http://localhost:8100";

    //--------------------------------- START OF HELP-METHODS ------------------------------------------------


    public static void waitForObject(WebDriver driver, String id, boolean useId) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (useId) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
        }
    }

    public static void waitForDom(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static void scrollToElement(WebDriver driver, String id, boolean useId) {
        waitForDom(driver);
        WebElement element;
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        if (useId) {
            element = (WebElement)executor.executeScript("return document.getElementById('" + id + "')");
        } else {
            element = driver.findElement(By.xpath(id));
        }

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void clickOnObject(WebDriver driver, String id, boolean useId) {
        waitForDom(driver);
        scrollToElement(driver, id, useId);
        waitForObject(driver, id, useId);
        WebElement element;
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        if (useId) {
            element = (WebElement)executor.executeScript("return document.getElementById('" + id + "')");
        } else {
            element = driver.findElement(By.xpath(id));
        }

        try {
            Object outerH = executor.executeScript("return window.outerHeight");
            Object innerH = executor.executeScript("return window.innerHeight");

            int difH = Integer.parseInt(outerH.toString()) - Integer.parseInt(innerH.toString());

            TimeUnit.SECONDS.sleep(1);
            Point coordinates = element.getLocation();
            Robot robot = new Robot();
            robot.mouseMove(coordinates.getX()+5,coordinates.getY()+difH+5);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (StaleElementReferenceException | AWTException | InterruptedException e) {
            clickOnObject(driver, id, useId);
        }
    }

    public static boolean checkIfObjectExistis(WebDriver driver, String id) {
        waitForObject(driver, id, true);
        WebElement element = driver.findElement(By.id(id));
        return element.isDisplayed();
    }

    public static void openWebsite(WebDriver driver, String arg1) {
        driver.get(arg1);
        driver.manage().window().maximize();
    }

    public static void gotToRegistrationPage(WebDriver driver) {
        openWebsite(driver, URL);
        clickOnObject(driver, "createAccountButton", true);
    }

    public static void createUser(WebDriver driver, String name, String email, String pin) {
//        writeInInputField(driver, name, "accountName");
//        writeInInputField(driver, email, "accountEMail");
//        writeInInputField(driver, pin, "accountPIN");
        clickOnObject(driver, "createUserButton", true);
    }

    public static void loginUser(WebDriver driver, String email, String password) {
        openWebsite(driver, URL);
        writeInInputField(driver, email, "mat-input-0", true);
        writeInInputField(driver, password, "mat-input-1", true);
        clickOnObject(driver, "loginButton", true);
    }

    public static void writeInInputField(WebDriver driver, String value, String id, boolean useId) {
        //waitForObject(driver, id, useId);
        waitForDom(driver);
        waitForObject(driver, id, useId);
        clickOnObject(driver, id, useId);

        WebElement element;
        if (useId) {
            element = driver.findElement(By.id(id));
        } else {
            element = driver.findElement(By.xpath(id));
        }
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].value = '" + value + "';", element);
        executor.executeScript("var event = document.createEvent('Event');\n" +
                "event.initEvent('input', true, true);\n" +
                "arguments[0].dispatchEvent(event);", element);
    }
}
