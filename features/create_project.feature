Feature: Create new project
  Description: A new project is created in the system
  Actors: Developer

#  Scenario: Create project with name successfully
#    Given that there is a project with title "New Software Project" and starting date "02-11-2022"
#    When the project is added
#    Then a project with title "New Software Project" and starting date "02-11-2022" is in the sytem
#    And there are no projects with the same project number

#  Scenario: Project with name exists in database
#    Given that there is a project with title "New Software Project" and starting date "02-11-2022"
#    And there is a project in app with title "New Software Project" and starting date "02-11-2022"
#    When the project is added
#    Then the error "Project already exists" is given

#  Scenario: Create project with no name
#    Given that there is a project with no name and starting date "02-11-2022"
#    When the project is added
#    Then there is a new project with no name and starting date "02-11-2022" in the system
#    And there are no projects with the same project number

#  Scenario: Create project with expired starting date
#    Given that there is a project with no name and starting date "02-11-1979"
#    When the project is added
#    Then the error "Starting date has expired" is given