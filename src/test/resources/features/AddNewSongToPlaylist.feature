Feature: User should be able to add new song to playlist
  Scenario Outline: Add New Song to Playlist
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    When I search a song "take my hand"
    And I tap View All
    And I select first song on list
    And I add song to existing playlist "<playlistName>"
    Then I should see Song added to playlist message
    Examples: email and password
      | email                          | password       | playlistName |
      | yelyzaveta.postnova@testpro.io | YrkeNi92       | New Playlist |
