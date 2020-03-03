package hilfsmethoden;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class hilfsmethoden {

    private final static String URL = "http://localhost:8100";

    //--------------------------------- START OF HELP-METHODS ------------------------------------------------


    public static void waitForObject(WebDriver driver, String id) {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public static void clickOnObject(WebDriver driver, String id) {
        waitForObject(driver, id);
        WebElement element = driver.findElement(By.id(id));
        try {
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException e) {
            clickOnObject(driver, id);
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
        clickOnObject(driver, "registrationButton");
    }

    public static void createUser(WebDriver driver, String name, String email, String pin) {
        writeInInputField(driver, name, "accountName");
        writeInInputField(driver, email, "accountEMail");
        writeInInputField(driver, pin, "accountPIN");
        clickOnObject(driver, "createUserButton");
    }

    public static void writeInInputField(WebDriver driver, String value, String id) {
        waitForObject(driver, id);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('" + id + "').value = '" + value + "';");
    }
}
