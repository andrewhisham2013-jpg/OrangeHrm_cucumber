Feature: Employee Profile - Login Simulation

  Scenario: ESS Employee logs into the system with restricted portal credentials
    Given the user logs in as an ESS employee with configuration parameters
    Then the user transitions to the PIM My Info Personal Details screen successfully