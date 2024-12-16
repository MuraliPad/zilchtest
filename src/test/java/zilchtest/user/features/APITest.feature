@apitest
Feature: Validate API and Mobile Functionality

  Scenario: Verify API response
    Given I call the jsonplaceholder api with posts route
    Then I validate the reponse code
    Then I verify output contains valid user id