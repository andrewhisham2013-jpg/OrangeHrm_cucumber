Feature: Employee Dependents - Login Navigation

  Scenario: Admin successfully logs in and locates the detailed info profile for an employee
    Given the admin enters system credentials and accesses the detailed info panel for employee "Andrew Hisham"
    Then the system should confirm the employee info panel loaded successfully