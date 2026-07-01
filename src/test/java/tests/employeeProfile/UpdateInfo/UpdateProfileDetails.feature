Feature: Employee Profile - Personal Details Modification

  Scenario: Authenticated employee modifies personal information fields and commits changes
    Given the employee profile wizard updates fields including contact details, emergency contacts, marital status, and nationality
    Then the user cleanly logs out of the current active employee session