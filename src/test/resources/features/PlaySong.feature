Feature: User should be able to play a song
  Scenario Outline: Play Song
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    When I click PlayNext button
    And I click Play button
    Then song should be playing
    Examples: email and password
      | email                          | password    |
      | yelyzaveta.postnova@testpro.io | YrkeNi92    |
      | demo@class.com                 | te$t$tudent |