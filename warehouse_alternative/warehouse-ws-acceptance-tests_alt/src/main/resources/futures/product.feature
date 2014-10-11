Feature: Product
  
  Scenario: Create product
    When the client requests POST to product resource
    Then the response must be valid from product resource
    
  @product  
  Scenario: Get product by id    
    When the client requests GET to product resource
    Then the response must be valid from product resource

  @product
  Scenario: Update product by id    
    When the client requests PUT to product resource
    Then the response must be valid from product resource 
    Then the response must contain updated fields from product resource 
    When the client requests GET to product resource
    Then the response must be valid from product resource   

  @product
  Scenario: Delete product by id   
    When the client requests DELETE to product resource
    Then the response must be valid from product resource
    When the client requests GET to product resource
    Then the response exception status code should be "422" from product resource 

  Scenario: Get product by invalid id
    When the client requests GET with invalid id to product resource
    Then the response should be exception from product resource
    Then the response exception status code should be "422" from product resource