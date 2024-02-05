Feature: User should be able to register in application
  Scenario: Redirect to Registration page
    Given I open Login page
    When I tap registration link
    Then I should be redirected to registration page
