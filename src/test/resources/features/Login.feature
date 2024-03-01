Feature: As a user I want to be able to log into my account so I can use Koel app
  Background: User opens Koel Login page
    Given I open Login page
    And I click Forgot password link
    And I provide email for password reset
    And I navigate to Reset password link in my email
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
