Feature: User should be able to create playlist
  Scenario Outline: Create Simple Playlist
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    When I select Create Simple Playlist option
    And I enter "<playlistName>"
    Then I should see Playlist created message for "<playlistName>"
    Examples: email and password
      | email                          | password    | playlistName |
      | yelyzaveta.postnova@testpro.io | YrkeNi92    | New Playlist |