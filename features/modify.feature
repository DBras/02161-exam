# Jonathan
Feature: Modify Activity
  Description: Modifiy activity in the project
  Actors: Project manager

  Scenario: Modification successful
    And there is a new activity with the title "New Project", expected time 10, start year 2022, start week 35, and end week 42
    When the activity is changed to "software_engineering", start year 2023, start week 15, and end week 17
    Then the activity has name "software_engineering", start year 2023, start week 15, and end week 17
    
  Scenario: New date is in the past
    And there is a new activity with the title "New Project", expected time 10, start year 2022, start week 35, and end week 42
    When the activity is changed to "software_engineering", start year 2021, start week 15, and end week 17
    Then The error message "Activity starting date is expired" is given
