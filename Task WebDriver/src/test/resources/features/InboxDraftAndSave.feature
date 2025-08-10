Feature: Gmail Draft Save and Send functionality

  Background:
    Given I navigate to the Gmail home page
    And I log in as a simple user

  @regression
  Scenario Outline: Save email draft
    When I click on the "Compose" button
    And I set text in the "Recipients" field to "<Email>>"
    And I set text in the "Subject" field to "<Subject>>"
    And I set text in the "Message" field to "<Message>"
    And I click on the "Close Mail Frame" button
    And I click on the "Draft list" button
    And I click on the "First message on the list" button
    Then I should see the "Recipients" field with text "{test.user_email}"
    And I should see the "Subject" field with text "{random_subject}"
    And I should see the "Message" field with text "{random_message}"
    Examples:
      | Email                     | Subject | Message |
      | testuser001gaa2@gmail.com | Sub01   | Mess01  |
      | testuser001gaa2@gmail.com | Sub02   | Mess02  |
