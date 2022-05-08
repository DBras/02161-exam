# Daniel
Feature: Assign project manager
  Description: Assign a manager to a project
  Actors: Developer

  Scenario: Assign manager to a project with no previous manager
    Given that there is a project with title "New Software Project" and starting date "2028-02-28"
    And the project has no manager
    And there is a developer with initials "hulk"
    When the  developer is assigned as project manager
    Then the project manager is "hulk"

  Scenario: Assign manager to project with previous manager
    Given that there is a project with title "New Software Project" and starting date "2028-02-28"
    And the project already has the manager "blob"
    And there is a developer with initials "hulk"
    When the  developer is assigned as project manager
    Then the project manager is "hulk"