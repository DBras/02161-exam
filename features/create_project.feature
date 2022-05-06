Feature: Create new project
  Description: A new project is created in the system
  Actors: Developer

  Scenario: Create project with name successfully
    Given that there is a project with title "New Software Project" and starting date "2022-11-02"
    When the project is added
    Then a project with title "New Software Project" and starting date "2022-11-02" is contained in the system
    And there are no two projects with the same project number in the system

  Scenario: Create project with no name
    Given that there is a project with no name and starting date "2022-11-02"
    When the project is added
    Then there is a new project with no name and starting date "2022-11-02" in the system
    And there are no two projects with the same project number in the system

  Scenario: Create project with expired starting date
    Given that there is a project with no name and starting date "1979-02-11"
    When the project is added
    Then the error "Project starting date has expired" is given

  Scenario: Project with name and date already exists in database
    Given that there is a project with title "New Software Project" and starting date "2022-11-02"
    And these projects are contained in the system
        | New Software Project | 2022-11-02 |
        | Other Project        | 2022-08-23 |
        | Third Project        | 2022-11-02 |
        | An Actual Project    | 2024-01-01 |
    When the project is added
    Then the error "Project already exists in the system" is given
