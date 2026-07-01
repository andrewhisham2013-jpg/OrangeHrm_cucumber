Feature: User Assignment - Authentication

  Scenario: Admin successfully authenticates with system configuration credentials
    Given the admin user requests authentication using configuration properties
    Then the user should be redirected to the main dashboard module safely