Feature: login

  Scenario Outline: Login with valid credentials
    Given site is opened
    When enter "<user>" and "<pass>"
    And click on login button
    Then home title is appeared
    Examples:
      |user|pass|
      |username|password|
