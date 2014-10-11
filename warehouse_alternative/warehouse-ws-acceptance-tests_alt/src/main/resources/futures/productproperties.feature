Feature: Product properties
    
  Scenario: Create product properties
    When the client requests POST to product properties resource
    Then the response must be valid from product properties resource
    
  @productproperties
  Scenario: Get product properties by name    
    When the client requests GET to product properties resource
    Then the response must be valid from product properties resource
 
  @productproperties 
  Scenario: Update product properties by name   
    When the client requests PUT to product properties resource
    Then the response must be valid from product properties resource
    Then the response must contain updated fields from product properties resource
    When the client requests GET to product properties resource
    Then the response must be valid from product properties resource  

  @productproperties
  Scenario: Delete product properties by name    
    When the client requests DELETE to product properties resource
    Then the response must be valid from product properties resource
    When the client requests GET to deleted product properties resource
    Then the response exception status code should be "422" from product properties resource    
    
  Scenario: Get product properties by invalid name
    When the client requests GET with invalid id to product properties resource
    Then the response should be exception from product properties resource
    Then the response exception status code should be "422" from product properties resource