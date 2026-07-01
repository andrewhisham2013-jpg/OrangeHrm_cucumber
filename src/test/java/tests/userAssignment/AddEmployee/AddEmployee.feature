Feature: User Assignment - Add Employee Profile

  Scenario: Admin successfully creates a new employee profile with login details
    Given the admin navigates to the PIM creation screen
    When the admin populates the creation wizard fields with employee details
    Then the user saves the profile safely