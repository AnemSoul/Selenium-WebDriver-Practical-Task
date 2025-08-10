Feature: Gmail Sign-In functionality

  Background:
    Given I navigate to the Gmail home page

  @smoke
  Scenario: Sign in to Gmail with valid credentials
    When I click on the "Sign in" button
    And I set text in the "Email" field to "testuser001gaa2@gmail.com"
    And I click on the "Email Next" button
    And I set text in the "Password" field to "Test@1234"
    And I click on the "Password Next" button
    Then I should see the "Gmail Inbox" page loaded