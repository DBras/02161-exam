# Jonathan
Feature: Modify Activity
  Description: Modifiy activity in the project
  Actors: Project manager

  Scenario: Modification successful
    Given that there is an activity with name "New Project" and start date "2022-06-05"
    When the activity is changed to "software_engineering" and date "2022-08-20"
    Then the activity has name "software_engineering" and date "2022-08-20"
    
  Scenario: New date is in the past
    Given that there is an activity with name "New Project" and start date "2022-06-05"
    When the activity is changed to "software_engineering" and date "2022-04-20"
    Then The error message "Activity starting date is expired" is given
