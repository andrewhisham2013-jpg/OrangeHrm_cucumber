Feature: Employee Emergency Contacts and Dependent Management

  Scenario: Update and verify emergency contacts and dependents as Admin HR
    Given the user logs in as Admin HR to manage records
    And the admin navigates to the PIM Employee List and selects the target employee
    When the admin adds a new emergency contact and edits existing contacts
    And the admin navigates to dependents to add a new dependent record
    Then the admin verifies that both emergency contacts and dependents are correctly saved