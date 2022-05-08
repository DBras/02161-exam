Feature: Add development employee (Johannes)
    Description: a development
    Actors: Project manager
	

   Scenario: Add a develoment employee successfully	
	 Given there is an a new developer called "Lars"
	 When the developer called "Lars" is added to the system
	 Then the devolomer with name "Lars" is part of the of the system

	 #Scenario: add an existing developer succesfully
	 #Given there is a developer called "JBN"
	 #And these developers are in the system
		#		| JBN |
    #    | HB  |
    #    | EIB |
    #    | SDN |
   #When the developer is added to the system
   #Then The error message "Developer already exist in the system" is given
		        
	 