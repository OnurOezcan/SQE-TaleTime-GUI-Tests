# language: en
Feature: This .ferature file checks the functionality of the graphical user interface of the TaleTime application.


  Scenario: This scenario is used to check the availability of the Web site.
    Given is the URL "http://localhost:8100".
    When the URL is entered in the browser,
    Then the TaleTime startpage should appear.

  Scenario: This scenario checks if the user can navigate from the start page to the Registration-Form
    Given is the URL "http://localhost:8100".
    When the user clicks on Registration-Button
    Then the Registration-Form should open

  Scenario: Check if an account can be created
    Given are the following values:
      | Designation: | Value:                |
      | mat-input-2  | Tester                |
      | mat-input-3  | Tester@htwsaarTest.de |
      | mat-input-4  | 123456789             |
      | mat-input-5  | 123456789             |
    When The button Create was clicked with these values
    Then a new user should exist.

#  Scenario: Check if a new profile can be created within an account.
#    Given  a logged-in user
#    When a new profile called "Test Child" is created
#    Then a new profile should be available
#
#  Scenario: Check if User can add multiple profiles.
#    Given a logged-in user
#    And a existing profile
#    When a new profile called "Second Child" is created
#    Then a new profile should be available
#
#  Scenario: Testing if user can Log out
#    Given a logged-in user
#    When user types in his pin under "my Account"
#    And clicks on Logout in the next page
#    Then the TaleTime startpage should appear.
#
#  Scenario: Check if a existing User can Login.
#  #  Given a logged-in user
#    When User Logs out
#    And  types types in his credentials on Login Page
#    Then he should logged in
#
#  Scenario: Check if User can add new Stories
#   # Given a logged-in user
#    When User adds new Storie Titles
#    Then the new Stories should be available in the "story-menu" section
#
#  Scenario: Testing the possibility to change the font size
#   # Given a logged-in user
#    When User changes Font-Size to 14px
#    Then Font size is 14px
#
#  Scenario: Testing if user can switch the selected language
#   # Given a logged-in user
#    When User changes the Language from "de" to "en"
#    Then the Application should be in English
#
#  Scenario: Testing if user can delete his account
#   # Given a logged-in user
#    When user typed in hin pin under "myAccount"
#    And clicks on delete account
#    Then he should not able to login again
