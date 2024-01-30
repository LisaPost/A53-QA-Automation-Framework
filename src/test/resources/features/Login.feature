Feature: User should be able to login
  Background:
    Given I open Login page

  Scenario Outline: Login Success
    When I enter email "<email>"
    And I enter password "<password>"
    And I submit
    Then I am logged in
    Examples: email and password
    | email                          | password       |
    | yelyzaveta.postnova@testpro.io | YrkeNi92       |
    | demo@class.com                 | te$t$tudent    |

  Scenario Outline: Login Failed
    When I enter email "<email>"
    And I enter password "<password>"
    And I submit
    Then I am not logged in
    Examples: email and password
      | email                          | password       |
      | yelyzaveta.postnova@testpro.io | 12345678       |
      | demo@class.com                 | YrkeNi92       |
      | yelyzaveta.postnova@testpro.io |                |
      |                                | YrkeNi92       |
      |                                |                |
