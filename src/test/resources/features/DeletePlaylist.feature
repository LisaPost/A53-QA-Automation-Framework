Feature: User should be able to delete playlist
  Scenario Outline: Delete Empty Playlist
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Simple Playlist option
    And I enter playlistName "<emptyPlaylistToDelete>"
    And I select empty playlist "<emptyPlaylistToDelete>"
    When I tap Delete Empty Playlist button
    Then empty playlist "<emptyPlaylistToDelete>" should be deleted
    Examples: email and password
      | email                          | password       | emptyPlaylistToDelete |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       | Playlist to Delete    |

  Scenario Outline: Delete Playlist With Song
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Simple Playlist option
    And I enter playlistName "<playlistToDelete>"
    And I search a song "<song>"
    And I tap View All
    And I select first song on list
    And I add song to playlist "<playlistToDelete>"
    And I select playlist with song "<playlistToDelete>"
    When I tap Delete Playlist button
    Then playlist with song "<playlistToDelete>" should be deleted
    Examples: email and password
      | email                          | password | playlistToDelete             | song         |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | Playlist with song to Delete | take my hand |