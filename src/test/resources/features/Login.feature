Feature: As a user I want to be able to log into my account so I can use Koel app
  Background: User opens Koel Login page
    Given I open Login page
    And I click Forgot password link
    And I provide email for password reset
    And I navigate to Reset password link in my email
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
      | demo@class.com                 | te$t$tudent    |

  Scenario Outline: User is navigated to Home Page after successful login
    When I enter email "<email>"
    And I enter password "<password>"
    And I submit
    Then I should be redirected to Home page
    Examples: email and password
      | email                          | password       |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       |
      | demo@class.com                 | te$t$tudent    |

  Scenario Outline: Login Success with updated email
    And I log into app with valid "<email>" and "<password>"
    And I tap avatar icon
    And I provide current "<password>"
    When I provide "<new_email>"
    And I click Save
    And I log out
    And I enter email "<new_email>"
    And I enter password "<password>"
    Then I am logged in
    Examples: email and password
      | email                          | password       | new_email               |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       | flower_rose@outlook.com |

  Scenario Outline: Login Success with updated password
    And I log into app with valid "<email>" and "<password>"
    And I tap avatar icon
    And I provide current "<password>"
    When I provide "<new_password>"
    And I click Save
    And I log out
    And I enter email "<email>"
    And I enter password "<new_password>"
    Then I am logged in
    Examples: email and password
      | email                          | password       | new_password |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       | te$t$tudent  |

  Scenario Outline: Login Failed with old email
    And I log into app with valid "<email>" and "<password>"
    And I tap avatar icon
    And I provide current "<password>"
    When I provide "<new_email>"
    And I click Save
    And I log out
    And I enter email "<email>"
    And I enter password "<password>"
    Then I am not logged in
    And I should see login error
    Examples: email and password
      | email                          | password       | new_email             |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       | backstage@outlook.com |

  Scenario Outline: Login Failed with old password
    And I log into app with valid "<email>" and "<password>"
    And I tap avatar icon
    And I provide current "<password>"
    When I provide "<new_password>"
    And I click Save
    And I log out
    And I enter email "<email>"
    And I enter password "<password>"
    Then I am not logged in
    And I should see login error
    Examples: email and password
      | email                          | password       | new_password |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       | te$t$tudent  |

  Scenario Outline: Login Failed with unregistered credentials
    When I enter email "<email>"
    And I enter password "<password>"
    And I submit
    Then I should see login error
    Examples: email and password
      | email                      | password    |
      | tree@class.com             | te$t$tudent |

  Scenario Outline: Login Failed with invalid credentials
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
