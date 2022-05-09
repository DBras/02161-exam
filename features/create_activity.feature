Feature: Add development employee (Johannes)
    Description: a development
    Actors: Project manager
    
   Scenario: Add an activity successfully	
	 Given that there is a project with name "New Project" and start date "2022-06-05"
	 And there is a new activity with the title "activity1", expected time 10, start year 2022, start week 35, and end week 42
     When the activity is added to the project
	 Then the activity with title "activity1", expected time 10, start year 2022, start week 35, and end week 42 is contained in the project
   
    
