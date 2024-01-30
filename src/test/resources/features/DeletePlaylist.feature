Feature: User should be able to delete playlist
  Scenario Outline: Delete Playlist
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I select playlist "<playlistName>"
    When I tap Delete Playlist button
    Then "<playlistName>" should be deleted
    Examples: email and password
      | email                          | password       | playlistName |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       | New Playlist |