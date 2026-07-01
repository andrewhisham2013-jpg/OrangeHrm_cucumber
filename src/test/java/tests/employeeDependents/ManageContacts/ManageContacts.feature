Feature: Employee Dependents - Manage Emergency Contacts

  Scenario: Admin logs in, accesses employee profile, and updates emergency contacts
    Given the admin authenticates and navigates to the emergency contacts interface for employee "Andrew Hisham"
    When the admin creates a new emergency contact record with configured details
    Then the admin updates the existing contact mobile record safely