Feature: As a user I want to create a Smart playlist in app
  so that I can enjoy the music and modify my settings and preferences
  Scenario Outline: User should be able to create Smart Playlist with one rule
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    When I select Create Smart Playlist option
    And I type playlist name "<smartPlaylistName>"
    And I provide first rule Title contains "<text>"
    And I save smart playlist
    Then smart playlist "<smartPlaylistName>" should be created
    Examples: email password smartPlaylistName text
      | email                          | password | smartPlaylistName      | text |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | OneRule Smart Playlist | hand |

  Scenario Outline: User should not be able to create Smart Playlist with no rules
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Smart Playlist option
    And I type playlist name "<noRulePlaylistName>"
    When I remove rule
    And I save smart playlist
    Then smart playlist "<noRulePlaylistName>" should not be created
    Examples: email password smartPlaylistName
      | email                          | password | noRulePlaylistName    |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | NoRule Smart Playlist |

  Scenario Outline: User should be able to create Smart Playlist with multiple rules
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    When I select Create Smart Playlist option
    And I type playlist name "<smartPlaylistName>"
    And I provide first rule Title contains "<firstText>"
    And I provide second rule Artist is "<secondText>"
    And I provide third rule Length is greater than "<secondsAmount>"
    And I save smart playlist
    Then smart playlist "<smartPlaylistName>" should be created
    Examples: email password smartPlaylistName firstText secondText secondsAmount
      | email                          | password | smartPlaylistName        | firstText | secondText | secondsAmount |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | MultiRule Smart Playlist | hand      | Lobo Loco  | 60            |

  Scenario Outline: User should be able to create Smart Playlist with Group
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    When I select Create Smart Playlist option
    And I type playlist name "<smartPlaylistName>"
    And I provide first rule Title contains "<firstText>"
    And I provide group rule Title contains "<secondText>"
    And I save smart playlist
    Then smart playlist "<smartPlaylistName>" should be created
    Examples: email password smartPlaylistName firstText secondText
      | email                          | password | smartPlaylistName        | firstText | secondText |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | GroupRule Smart Playlist | hand      | birthday   |

  Scenario Outline: User should be able to create empty Smart Playlist if none of rules fits
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    When I select Create Smart Playlist option
    And I type playlist name "<smartPlaylistName>"
    And I provide first rule Title contains "<firstText>"
    And I provide second rule Artist is "<secondText>"
    And I provide third rule Length is greater than "<secondsAmount>"
    And I save smart playlist
    Then smart playlist "<smartPlaylistName>" should be created
    And No songs match the playlists criteria message should be displayed for "<smartPlaylistName>"
    Examples: email password smartPlaylistName firstText secondText secondsAmount
      | email                          | password | smartPlaylistName    | firstText | secondText | secondsAmount |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | Empty Smart Playlist | love      | Lobo Loco  | 600           |

  Scenario Outline: User should be able to cancel Smart Playlist creation
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Smart Playlist option
    And I type playlist name "<smartPlaylistName>"
    And I provide first rule Title contains "<firstText>"
    When I cancel smart playlist creation
    And I submit smart playlist cancellation
    Then no playlist with name "<smartPlaylistName>" should exist
    Examples: email password smartPlaylistName firstText
      | email                          | password | smartPlaylistName       | firstText |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | Canceled Smart Playlist | love      |

  Scenario Outline: Smart Playlist should not be created if user closes app
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Smart Playlist option
    And I type playlist name "<smartPlaylistName>"
    And I provide first rule Title contains "<firstText>"
    When I close browser
    And I open browser
    And I open Login page
    And I log into app with valid "<email>" and "<password>"
    Then no playlist with name "<smartPlaylistName>" should exist
    Examples: email password smartPlaylistName firstText
      | email                          | password | smartPlaylistName | firstText |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | Close Playlist    | hand      |

  Scenario Outline: User should be able to discard Smart Playlist cancellation
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Smart Playlist option
    And I type playlist name "<smartPlaylistName>"
    And I provide first rule Title contains "<firstText>"
    And I cancel smart playlist creation
    When I discard smart playlist cancellation
    Then I still should see smart playlist creation form
    And playlist name "<smartPlaylistName>" should remain in form
    And first rule Title contains "<firstText>" should remain in form
    Examples: email password smartPlaylistName firstText
      | email                          | password | smartPlaylistName    | firstText |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | Discard Cancellation | love      |

  Scenario Outline: Smart Playlist with 257 characters name should not be created
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Smart Playlist option
    When I enter playlist name "<numberOfChars>" characters length
    And I provide first rule Title contains "<firstText>"
    And I save smart playlist
    Then smart playlist should not be created
    Examples: email password numberOfChars firstText
      | email                          | password | numberOfChars | firstText |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | 257           | hand      |

  Scenario Outline: Smart Playlist with no name should not be created
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    When I select Create Smart Playlist option
    And I provide first rule Title contains "<firstText>"
    And I save smart playlist
    Then I still should see smart playlist creation form
    Examples: email password firstText
      | email                          | password | firstText |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | hand      |

  Scenario Outline: Smart Playlist with 1-256 characters name of letters numbers special should be created
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Smart Playlist option
    When I enter playlist name "<numberOfChars>" characters length
    And I provide first rule Title contains "<firstText>"
    And I save smart playlist
    Then smart playlist should be created
    Examples: email password numberOfChars firstText
      | email                          | password | numberOfChars | firstText |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | 256           | birthday  |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | 10            | hand      |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | 1             | day       |

  Scenario Outline: User should see related songs in Smart Playlist created with a rule
    Given I open Login page
    And I log into app with valid "<email>" and "<password>"
    And I click Create Playlist button
    And I select Create Smart Playlist option
    And I type playlist name "<smartPlaylistName>"
    When I provide first rule Title contains "<firstText>"
    And I save smart playlist
    And I select playlist "<smartPlaylistName>"
    Then I should see related songs with "<firstText>" in playlist
    Examples: email password smartPlaylistName firstText
      | email                          | password | smartPlaylistName | firstText |
      | yelyzaveta.postnova@testpro.io | YrkeNi92 | Related Songs     | day       |