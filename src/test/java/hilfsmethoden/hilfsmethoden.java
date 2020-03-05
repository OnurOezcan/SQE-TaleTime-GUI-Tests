package hilfsmethoden;

import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;


public abstract class hilfsmethoden {

    private final static String URL = "http://localhost:8100";

    //--------------------------------- START OF HELP-METHODS ------------------------------------------------


//    public static void waitForObject(WebDriver driver, String id, boolean useId) {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        if (useId) {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
//        } else {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
//        }
//    }

    public static void waitForDom(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static void changeId(WebDriver driver, String oldId, String newId) {
        waitForDom(driver);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.getElementById('" + oldId + "').setAttribute('id', '"+ newId +"')");
    }

    public static void clickOnObject(WebDriver driver, String id, boolean useId) {
        //waitForObject(driver, id, useId);
        waitForDom(driver);
        WebElement element;
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        if (useId) {
            //Object t = executor.executeScript("return document.getElementById('createAccountButton')", id);
            element = (WebElement)executor.executeScript("return document.getElementById('" + id + "')");
            //element = driver.findElement(By.id(id));
        } else {
            element = driver.findElement(By.xpath(id));
        }

        try {
//            Object x = executor.executeScript("return window.scrollX + arguments[0].getBoundingClientRect().left", element);
//            Object y = executor.executeScript("return window.scrollY + arguments[0].getBoundingClientRect().top", element);
            Point coordinates = element.getLocation();
            Robot robot = new Robot();
            robot.mouseWheel(3);
            robot.mouseMove(coordinates.getX()+100,coordinates.getY());
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//            Actions actions = new Actions(driver);
//            actions.moveToElement(element).click().perform();
//            //executor.executeScript("arguments[0].click();", element);
//            executor.executeScript("function eventFire(el, etype){\n" +
//                    "  if (el.fireEvent) {\n" +
//                    "    el.fireEvent('on' + etype);\n" +
//                    "  } else {\n" +
//                    "    var evObj = document.createEvent('Events');\n" +
//                    "    evObj.initEvent(etype, true, false);\n" +
//                    "    el.dispatchEvent(evObj);\n" +
//                    "  }\n" +
//                    "}" +
//                    "function Sleep(milliseconds) {\n" +
//                    "   return new Promise(resolve => setTimeout(resolve, milliseconds));\n" +
//                    "}" +
//                    "eventFire(arguments[0], 'mousedown');" +
//                    "await Sleep(100);" +
//                    "eventFire(arguments[0], 'mouseup');" +
//                    "await Sleep(100);" +
//                    "eventFire(arguments[0], 'click');", element);
        } catch (StaleElementReferenceException | AWTException e) {
            clickOnObject(driver, id, useId);
        }
    }

    public static boolean checkIfObjectExistis(WebDriver driver, String id) {
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

    public static void writeInInputField(WebDriver driver, String value, String id, boolean useId) {
        //waitForObject(driver, id, useId);
        waitForDom(driver);
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
