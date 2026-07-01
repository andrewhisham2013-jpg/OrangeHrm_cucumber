Feature: Dashboard Navigation

  Scenario Outline: Verify navigating to dashboard
    Given site is opened
    When enter "<user>" and "<pass>"
    And click on login button
    And choosing product
    Then product itself page is displayed

    Examples:
      | user     | pass     |
      | username | password |