Feature: Employee Dependents - Manage Dependents

  Scenario: Admin logs in, accesses employee profile, and creates a dependent record
    Given the admin authenticates and opens the dependents management layout for employee "Andrew Hisham"
    When the admin populates the new dependent entries from the system configuration
    Then the system validates the presence of the freshly added dependent record in the results table