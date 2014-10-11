Feature: Order
  
  @order  
  Scenario: Create incoming order
    When the client requests incoming order resource
    Then the response must be valid from order resource
    
  @order
  Scenario: Create outgoing order
    When the client requests outgoing order resource
    Then the response must be valid from order resource
  
 
