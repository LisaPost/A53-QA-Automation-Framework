Feature: User should be able to add new song to playlist
  Scenario Outline: Add New Song to Playlist
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Simple Playlist option
    And I enter playlistName "<playlistToAddSong>"
    When I search a song "<song>"
    And I tap View All
    And I select first song on list
    And I add song to existing playlist "<playlistToAddSong>"
    Then I should see Song added to "<playlistToAddSong>" message
    Examples: email and password
      | email                          | password | playlistToAddSong  | song         |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | Playlist with Song | take my hand |