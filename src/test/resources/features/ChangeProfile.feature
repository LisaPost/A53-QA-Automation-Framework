Feature: User should be able to make profile changes
  Scenario Outline: Change Profile Name
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I tap avatar icon
    And I provide current "<password>"
    When I provide new name
    And I click Save
    Then profile name should be changed
    Examples: email and password
      | email                          | password       |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       |

