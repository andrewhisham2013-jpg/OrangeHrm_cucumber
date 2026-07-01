Feature: Employee Reports - Configuration Validation

  Scenario: Admin configures a custom report and verifies the record generation identity header
    Given the admin logs in and opens the primary PIM report configuration window
    When the admin submits custom structural reporting constraints named "Andrew Hisham report"
    Then the admin confirms that the generated reporting layout display header reflects "Andrew Hisham report"