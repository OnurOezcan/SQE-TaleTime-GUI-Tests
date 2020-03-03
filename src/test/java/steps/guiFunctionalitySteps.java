package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.List;

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
        waitForObject(driver, "startPageHeading");
        assert checkIfObjectExistis(driver, "startPageHeading");
        driver.quit();
    }

    @When("^the user clicks on Registration-Button$")
    public void theUserClicksOnRegistrationButton() throws Throwable {
        waitForObject(driver, "registrationButton");
        clickOnObject(driver, "registrationButton");
        assert true;
    }

    @Then("^the Registration-Form should open$")
    public void theRegistrationFormShouldOpen() throws Throwable {
        waitForObject(driver, "createUserButton");
        assert checkIfObjectExistis(driver, "createUserButton");
        driver.quit();
    }

    @Given("^are the following values:$")
    public void areTheFollowingValues(DataTable dataTable) throws Throwable {
        List<List<String>> data = dataTable.raw();
        gotToRegistrationPage(driver);
        for (int i = 1; i < data.size(); i++) {
            String description = data.get(i).get(0).trim();
            String value = data.get(i).get(1).trim();


            //DOES NOT WORK (REASON IS CURRENTLY UNKNOWN)
            //WebElement element = driver.findElement(By.id(description));
            //element.click();
            //element.sendKeys(value);

            //CURRENT WORKAROUND: EXECUTE JAVASCRIPT AND SET THE VALUE THERE
            ((JavascriptExecutor) driver).executeScript("document.getElementById('" + description + "').value = '" + value + "';");
        }
        assert true;
    }

    @When("^The button Create was clicked with these values$")
    public void theButtonCreateWasClicked() throws Throwable {
        clickOnObject(driver, "createUserButton");
        assert true;
    }

    @Then("^a new user should exist\\.$")
    public void aNewUserShouldExist() throws Throwable {
        assert checkIfObjectExistis(driver, "createUserHeader");
        driver.quit();
    }

    @Given("^a logged-in user$")
    public void aLoggedInUser() throws Throwable {
        gotToRegistrationPage(driver);
        createUser(driver, "aUser", "htw@sqe.de", "1234");
        assert true;
    }

    @When("^a new profile called \"([^\"]*)\" is created$")
    public void aNewProfileCalledIsCreated(String name) throws Throwable {
        //actually don't work
        clickOnObject(driver, "createProfileButton");
        writeInInputField(driver, name, "createProfileInput");
        clickOnObject(driver, "createProfileButton");
    }
/*
      @Then("^a new profile should be available$")
      public void aNewProfileShouldBeAvailable() throws Throwable {
          // Write code here that turns the phrase above into concrete actions
          throw new PendingException();
      }

      @Given("^a existing profile$")
      public void aExistingProfile() throws Throwable {
          // Write code here that turns the phrase above into concrete actions
          throw new PendingException();
      }

      @Given("^a existing User in Database$")
      public void aExistingUserInDatabase() throws Throwable {
          // Write code here that turns the phrase above into concrete actions
          throw new PendingException();
      }

      @When("^User types in his credentials$")
      public void userTypesInHisCredentials() throws Throwable {
          // Write code here that turns the phrase above into concrete actions
          throw new PendingException();
      }

      @Then("^he should logged in$")
      public void heShouldLoggedIn() throws Throwable {
          // Write code here that turns the phrase above into concrete actions
          throw new PendingException();
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

      @When("^user types in his pin under \"([^\"]*)\"$")
      public void userTypesInHisPinUnder(String arg1) throws Throwable {
          // Write code here that turns the phrase above into concrete actions
          throw new PendingException();
      }

      @When("^clicks on Logout in the next page$")
      public void clicksOnLogoutInTheNextPage() throws Throwable {
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
  */

}