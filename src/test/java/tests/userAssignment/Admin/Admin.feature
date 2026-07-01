Feature: User Assignment - Admin Edit Flow

  Scenario: Admin successfully onboard a new user with middle name details and modifies their profile
    Given the administrator logs into the system with core credentials
    When the administrator creates an employee with full names and unique login data
    Then the administrator saves the profile and immediately opens it for editing inside Admin module