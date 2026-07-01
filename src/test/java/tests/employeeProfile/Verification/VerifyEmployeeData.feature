Feature: Employee Personal Information Update and Verification

  Scenario: Employee updates personal info and HR verifies it
    Given the user logs in as an ESS employee
    And the user navigates to PIM My Info Personal Details
    When the user updates fields including contact details, emergency contacts, marital status, and nationality
    And the user logs out of the employee account
    And the user logs back in as Admin HR
    And the admin navigates to PIM Employee List and selects the updated employee
    Then the admin verifies that the employee changes are correctly saved