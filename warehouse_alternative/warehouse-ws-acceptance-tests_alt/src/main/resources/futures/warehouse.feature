Feature: Warehouse
  
  Scenario: Create warehouse
    When the client requests POST to warehouse resource
    Then the response must be valid from warehouse resource
    
  @warehouse
  Scenario: Get warehouse by id    
    When the client requests GET to warehouse resource
    Then the response must be valid from warehouse resource
 
  @warehouse 
  Scenario: Update warehouse by id   
    When the client requests PUT to warehouse resource
    Then the response must be valid from warehouse resource
    Then the response must contain updated fields from warehouse resource
    When the client requests GET to warehouse resource
    Then the response must be valid from warehouse resource  

  @warehouse
  Scenario: Delete warehouse by id    
    When the client requests DELETE to warehouse resource
    Then the response must be valid from warehouse resource
    When the client requests GET to warehouse resource
    Then the response exception status code should be "422" from warehouse resource    
  
  Scenario: Get warehouse by invalid id
    When the client requests GET with invalid id to warehouse resource
    Then the response should be exception from warehouse resource
    Then the response exception status code should be "422" from warehouse resource