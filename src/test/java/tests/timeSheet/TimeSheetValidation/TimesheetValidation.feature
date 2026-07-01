Feature: End-to-End Timesheet Submission and Validation Flow

  Scenario: Employee submits weekly time allocation matrix and Admin verifies logs
    Given the employee logs in and navigates to the timesheet editor module
    When the employee logs out and the admin authenticates into the dashboard
    Then the admin verifies that the recorded hours per day and total accumulation are accurate