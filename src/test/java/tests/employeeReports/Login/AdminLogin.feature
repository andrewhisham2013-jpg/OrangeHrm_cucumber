Feature: Employee Reports - Admin Authentication

  Scenario: Admin authenticates into the system and loads the dashboard
    Given the admin enters valid credentials from configuration files
    Then the admin verifies that the dashboard page loads successfully