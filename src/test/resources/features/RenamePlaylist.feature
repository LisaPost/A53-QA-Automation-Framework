Feature: User should be able to rename playlist
  Scenario Outline: Rename Playlist
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Simple Playlist option
    And I enter "<playlistToRename>"
    And I select "<playlistToRename>"
    And I rightclick on selected "<playlistToRename>"
    When I select Edit option
    And I enter new playlist name "<newPlaylistName>"
    Then playlist name should be changed to "<newPlaylistName>"
    Examples: email and password
      | email                          | password | playlistToRename   | newPlaylistName  |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | Playlist to Rename | Renamed Playlist |