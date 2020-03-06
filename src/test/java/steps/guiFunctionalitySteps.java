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
import org.openqa.selenium.opera.OperaDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static hilfsmethoden.hilfsmethoden.*;

public class guiFunctionalitySteps {

    private WebDriver driver = new ChromeDriver();
    private static String URL = "http://localhost:8100";

    //switch if you have opera
    //private WebDriver driver = new OperaDriver();

    // ------------------------------ CUCUMBER GLUE CODE ----------------------------------

    @Given("^is the URL \"([^\"]*)\"\\.$")
    public void isTheURL(String arg1) throws Throwable {
        openWebsite(driver, arg1);
        assert true;
    }

    @When("^the URL is entered in the browser,$")
    public void theURLIsEnteredInTheBrowser() throws Throwable {
        assert true;
    }

    @Then("^the TaleTime startpage should appear\\.$")
    public void theTaleTimeStartpageShouldAppear() throws Throwable {
        //waitForObject(driver, "startPageHeading", true);
        waitForDom(driver);
        assert checkIfObjectExistis(driver, "startPageHeading");
        driver.quit();
    }

    @When("^the user clicks on Registration-Button$")
    public void theUserClicksOnRegistrationButton() throws Throwable {
        clickOnObject(driver, "createAccountButton", true);
        assert true;
    }

    @Then("^the Registration-Form should open$")
    public void theRegistrationFormShouldOpen() throws Throwable {
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
    public void theButtonCreateWasClicked() throws Throwable {
        clickOnObject(driver, "registerAndCreateAccount", true);
        assert true;
    }

    @Then("^a new user should exist\\.$")
    public void aNewUserShouldExist() throws Throwable {
        checkIfObjectExistis(driver, "createUserHeader");
        assert driver.getCurrentUrl().equals("http://localhost:8100/select-user-profile");
        driver.quit();
    }

    @Given("^a logged-in user$")
    public void aLoggedInUser() throws Throwable {
//        gotToRegistrationPage(driver);
        loginUser(driver, "Tester@htwsaarTest.de", "123456789");
        assert true;
    }

    @When("^a new profile called \"([^\"]*)\" is created$")
    public void aNewProfileCalledIsCreated(String name) throws Throwable {
        //actually don't work
        waitForObject(driver, "createProfileButton", true);
        clickOnObject(driver, "createProfileButton", true);
        writeInInputField(driver, name, "createProfileInput", true);
        clickOnObject(driver, "createNewProfileButton", true);
    }

//    @Then("^a new profile should be available$")
    @Then("^\"([^\"]*)\" profile/s should be available$")
    public void aNewProfileShouldBeAvailable(int numberOfProfiles) throws Throwable {
        waitForDom(driver);
        waitForObject(driver, "selectUserTitle", true);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        ArrayList<Object> element =  (ArrayList<Object>)executor.executeScript("return document.getElementsByClassName('ng-star-inserted item md in-list ion-focusable hydrated')");

        Assert.assertNotNull(element);
        Assert.assertEquals(element.size(), numberOfProfiles);
        driver.quit();
    }

    @Given("^a existing profile$")
    public void aExistingProfile() throws Throwable {
        aNewProfileCalledIsCreated("Baby");
    }

    @Given("^a selected profile$")
    public void aSelectedProfile() {
        clickOnObject(driver, "//*[@class='ng-star-inserted item md in-list ion-focusable hydrated']", false);
    }

    @When("^user types in his pin under \"([^\"]*)\"$")
    public void userTypesInHisPinUnder(String arg1) throws Throwable {
        clickOnObject(driver, "tab-button-settings", true);
        clickOnObject(driver, "goToUserAccount", true);
        clickOnObject(driver, "logAccountOut", true);
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^clicks on Logout in the next page$")
    public void clicksOnLogoutInTheNextPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^User Logs out$")
    public void userLogsOut() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^types types in his credentials on Login Page$")
    public void typesTypesInHisCredentialsOnLoginPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a user has an registered account$")
    public void hasARegisteredAccount() throws Throwable {
        assert true;
    }

    @Given("^is on the start page$")
    public void isOnStartPage() throws Throwable {
        driver.navigate().to(URL);
        assert true;
    }

    @When("^User logs in$")
    public void userLogsIn() throws Throwable {
        aLoggedInUser();
    }

    @Then("^he should logged in$")
    public void heShouldLoggedIn() throws Throwable {
        checkIfObjectExistis(driver, "createUserHeader");
        assert driver.getCurrentUrl().equals("http://localhost:8100/select-user-profile");
        driver.quit();
    }

    @When("^User adds new Storie Titles$")
    public void userAddsNewStorieTitles() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the new Stories should be available in the \"([^\"]*)\" section$")
    public void theNewStoriesShouldBeAvailableInTheSection(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^User changes Font-Size to (\\d+)px$")
    public void userChangesFontSizeToPx(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Font size is (\\d+)px$")
    public void fontSizeIsPx(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^User changes the Language from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void userChangesTheLanguageFromTo(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the Application should be in English$")
    public void theApplicationShouldBeInEnglish() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^user typed in hin pin under \"([^\"]*)\"$")
    public void userTypedInHinPinUnder(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^clicks on delete account$")
    public void clicksOnDeleteAccount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^he should not able to login again$")
    public void heShouldNotAbleToLoginAgain() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}