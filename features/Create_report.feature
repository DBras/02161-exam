Feature: Create repport(Jonathan)
    Description: create repport for the project
    Actors: project manager




Scenario: Repport successfully created
     Given there is a project with description "name" and start date "2028-06-05"
     When the project report is created
     Then there is a file with name "name.txt"