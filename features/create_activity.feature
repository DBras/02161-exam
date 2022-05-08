Feature: Add development employee (Johannes)
    Description: a development
    Actors: Project manager
    
   Scenario: Add an activity successfully	
	 Given that there is a project with name "New Project" and start date "2022-06-05"
	 And there is a new activity with the title "activity1", start "2022-01-22"
   When the activity is added to the projekt
	 Then the activity with title "activity1", start "2022-01-22" is contained in the projekt
   
    
