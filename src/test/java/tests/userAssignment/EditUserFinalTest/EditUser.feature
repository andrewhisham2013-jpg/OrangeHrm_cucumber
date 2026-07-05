Feature: End-to-End Administrative Privilege Assignment Workflows

  Scenario: Complete end-to-end workflow from employee creation to administrative role upgrade and verification
    Given the user logs in using credentials from json
    And the user navigates to the PIM module and selects Add Employee
    When the user inputs the new employee name details and unique username configuration
    And the user saves the profile and modifies their access permissions in Admin page
    And the user logs out and re-authenticates with the upgraded profile
    Then the upgraded user should be able to access administrative controls safely