package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static hilfsmethoden.hilfsmethoden.*;

public class guiFunctionalitySteps {

    private WebDriver driver = new ChromeDriver();
    private static String URL = "http://localhost:8100/";

    //switch if you have opera
    //private WebDriver driver = new OperaDriver();

    // ------------------------------ CUCUMBER GLUE CODE ----------------------------------

    @Given("^is the URL \"([^\"]*)\"\\.$")
    public void isTheURL(String arg1) {
        openWebsite(driver, arg1);
        assert true;
    }

    @When("^the URL is entered in the browser,$")
    public void theURLIsEnteredInTheBrowser() {
        assert true;
    }

    @Then("^the TaleTime startpage should appear\\.$")
    public void theTaleTimeStartpageShouldAppear() {
        waitForDom(driver);
        Assert.assertEquals(URL, driver.getCurrentUrl());
        driver.quit();
    }

    @When("^the user clicks on Registration-Button$")
    public void theUserClicksOnRegistrationButton() {
        clickOnObject(driver, "createAccountButton", true);
        assert true;
    }

    @Then("^the Registration-Form should open$")
    public void theRegistrationFormShouldOpen() {
        waitForDom(driver);
        assert checkIfObjectExistis(driver, "createAccountButton");
        driver.quit();
    }

    @Given("^are the following values:$")
    public void areTheFollowingValues(DataTable dataTable) throws Throwable {
        List<List<String>> data = dataTable.raw();
        gotToRegistrationPage(driver);
        for (int i = 1; i < data.size(); i++) {
            String description = data.get(i).get(0).trim();
            String value = data.get(i).get(1).trim();

            writeInInputField(driver, value, description, true);
            TimeUnit.SECONDS.sleep(1);
        }
        assert true;
    }

    @When("^The button Create was clicked with these values$")
    public void theButtonCreateWasClicked() {
        clickOnObject(driver, "registerAndCreateAccount", true);
        assert true;
    }

    @Then("^a new user should exist\\.$")
    public void aNewUserShouldExist() {
        checkIfObjectExistis(driver, "createUserHeader");
        assert driver.getCurrentUrl().equals( URL + "select-user-profile");
        driver.quit();
    }

    @Given("^a logged-in user$")
    public void aLoggedInUser() {
//        gotToRegistrationPage(driver);
        loginUser(driver, "Tester@htwsaarTest.de", "123456789");
        assert true;
    }

    @When("^a new profile called \"([^\"]*)\" is created$")
    public void aNewProfileCalledIsCreated(String name) {
        //actually don't work
        waitForObject(driver, "createProfileButton", true);
        clickOnObject(driver, "createProfileButton", true);
        writeInInputField(driver, name, "createProfileInput", true);
        clickOnObject(driver, "createNewProfileButton", true);
    }

//    @Then("^a new profile should be available$")
    @Then("^\"([^\"]*)\" profile/s should be available$")
    public void aNewProfileShouldBeAvailable(int numberOfProfiles) {
        waitForDom(driver);
        waitForObject(driver, "selectUserTitle", true);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        ArrayList<Object> element =  (ArrayList<Object>)executor.executeScript("return document.getElementsByClassName('ng-star-inserted item md in-list ion-focusable hydrated')");

        Assert.assertNotNull(element);
        Assert.assertEquals(element.size(), numberOfProfiles);
        driver.quit();
    }

    @Given("^a existing profile$")
    public void aExistingProfile() {
        aNewProfileCalledIsCreated("Baby");
    }

    @Given("^a selected profile$")
    public void aSelectedProfile() {
        clickOnObject(driver, "//*[@class='ng-star-inserted item md in-list ion-focusable hydrated']", false);
    }

    @When("^user navigates to user account page")
    public void userNavigatesToAccountPage() {
        clickOnObject(driver, "tab-button-settings", true);
        clickOnObject(driver, "goToUserAccount", true);
        // Write code here that turns the phrase above into concrete actions
        waitForObject(driver, "settingPageTitle", true);
        Assert.assertEquals(URL + "user-account", driver.getCurrentUrl());
//        throw new PendingException();
    }

    @When("^clicks on Logout in the next page$")
    public void clicksOnLogoutInTheNextPage() {
        waitForDom(driver);
        waitForObject(driver, "userPageTitle", true);
        clickOnObject(driver, "//button[@class='mat-button mat-button-base mat-primary ng-star-inserted']/span[text()='Sign out']/parent::*", false);
        assert true;
    }

    @When("^User Logs out$")
    public void userLogsOut() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^types types in his credentials on Login Page$")
    public void typesTypesInHisCredentialsOnLoginPage() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a user has an registered account$")
    public void hasARegisteredAccount() {
        assert true;
    }

    @Given("^is on the start page$")
    public void isOnStartPage() {
        driver.navigate().to(URL);
        assert true;
    }

    @When("^User logs in$")
    public void userLogsIn() {
        aLoggedInUser();
    }

    @Then("^he should logged in$")
    public void heShouldLoggedIn()  {
        checkIfObjectExistis(driver, "createUserHeader");
        assert driver.getCurrentUrl().equals(URL + "select-user-profile");
        driver.quit();
    }

    @When("^User adds new Story Title$")
    public void userAddsNewStoryTitle() throws InterruptedException {
        waitForDom(driver);
        clickOnObject(driver, "storyMenuAddNewStory", true);
        TimeUnit.MILLISECONDS.sleep(250);
        clickOnObject(driver, "//ion-card[@class=\"ng-star-inserted sc-ion-card-md-h sc-ion-card-md-s md hydrated\"]", false);
        TimeUnit.MILLISECONDS.sleep(250);
        clickOnObject(driver, "//button[@class=\"alert-button ion-focusable ion-activatable sc-ion-alert-md\"]", false);
        assert true;
    }

    @Then("^the new Stories should be available in the \"([^\"]*)\" section$")
    public void theNewStoriesShouldBeAvailableInTheSection(String arg1) throws InterruptedException {
        clickOnObject(driver, "tab-button-story-menu", true);
        TimeUnit.MILLISECONDS.sleep(250);
        waitForObject(driver, "//ion-card[@class=\"ng-star-inserted sc-ion-card-md-h sc-ion-card-md-s md hydrated\"]", false);
        assert true;
    }

    @Given("^a added story$")
    public void aAddedStory() throws InterruptedException  {
        this.userAddsNewStoryTitle();
    }

    @When("^User changes Font-Size to (\\d+)px$")
    public void userChangesFontSizeToPx(int arg1) throws InterruptedException{
        waitForDom(driver);
        clickOnObject(driver, "tab-button-settings", true);
        clickOnObject(driver, "settingsFontSize", true);
        TimeUnit.SECONDS.sleep(1);
        waitForObject(driver, "alert-input-2-1", true);
        clickOnObject(driver, "alert-input-2-1", true);
        clickOnObject(driver, "//span[@class=\"alert-button-inner sc-ion-alert-md\" and contains(text(), \"Ok\")]", false);
        // Write code here that turns the phrase above into concrete actions
        assert true;
    }

    @Then("^Font size is (\\d+)px$")
    public void fontSizeIsPx(int arg1) throws Throwable {
        waitForDom(driver);
        clickOnObject(driver, "tab-button-story-menu", true);
        waitForDom(driver);
        clickOnObject(driver, "ion-button[@class=\"ion-color ion-color-success md button button-solid ion-activatable ion-focusable hydrated\"]", false);
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^User changes the Language from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void userChangesTheLanguageFromTo(String arg1, String arg2) throws InterruptedException {
        waitForDom(driver);
        clickOnObject(driver, "tab-button-settings", true);
        clickOnObject(driver, "settingsChangeLanguage", true);
        TimeUnit.SECONDS.sleep(1);
        clickOnObject(driver, "//div[@class=\"alert-radio-label sc-ion-alert-md\" and contains(text(), \"English\")]/parent::div/parent::*", false);
        clickOnObject(driver, "//span[@class=\"alert-button-inner sc-ion-alert-md\" and contains(text(), \"OK\")]", false);
        assert true;
    }

    @Then("^the Application should be in English$")
    public void theApplicationShouldBeInEnglish() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        waitForObject(driver, "ion-sel-0-lbl", true );
        WebElement element = driver.findElement(By.id("ion-sel-0-lbl"));
        Assert.assertEquals("Language", element.getText());
    }

    @When("^user typed in hin pin under \"([^\"]*)\"$")
    public void userTypedInHinPinUnder(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^clicks on delete account$")
    public void clicksOnDeleteAccount() throws Throwable {
        waitForDom(driver);
        waitForObject(driver, "userPageTitle", true);
        clickOnObject(driver, "//button[@class='mat-button mat-button-base mat-warn ng-star-inserted']/span[text()='Delete account']/parent::*", false);
        assert true;
    }

    @Then("^he should not able to login again$")
    public void heShouldNotAbleToLoginAgain() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}