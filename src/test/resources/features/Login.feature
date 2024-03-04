Feature: As a user I want to be able to log into my account so I can use Koel app
  Background: User opens Koel Login page
    Given I open Login page
    And I click Forgot password link
    And I provide email for password reset
    And I open outlook email
    And I enter outlook email address
    And I enter outlook password
    And I confirm to stay signed in
    And I navigate to Inbox messages
    And I open Reset Password Notification
    And I open Reset Password page
    And I set new password
    And I submit new password
    And I open Login page

  Scenario Outline: Login Success with registered credentials
    When I enter email "<email>"
    And I enter password "<password>"
    And I submit
    Then I am logged in
    Examples: email and password
      | email                          | password       |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       |
