package hilfsmethoden;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class hilfsmethoden {

    private final static String URL = "http://localhost:8100";

    //--------------------------------- START OF HELP-METHODS ------------------------------------------------


    public static void waitForObject(WebDriver driver, String id) {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(id))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public static void clickOnObject(WebDriver driver, String id) {
        waitForObject(driver, id);
        WebElement element = driver.findElement(By.id(id));
        element.click();
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
        waitForObject(driver, "createUserButton");
        waitForObject(driver, "accountEMail");
        waitForObject(driver, "accountPIN");
        waitForObject(driver, "accountName");
    }

    public static void createUser(WebDriver driver, String name, String email, String pin) {

        ((JavascriptExecutor) driver).executeScript("document.getElementById('accountName').value = '" + name + "';");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('accountEMail').value = '" + email + "';");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('accountPIN').value = '" + pin + "';");
        clickOnObject(driver, "createUserButton");
    }

    public static void writeInInputField(WebDriver driver, String value, String id) {
        waitForObject(driver, id);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('" + id + "').value = '" + value + "';");
    }
}
