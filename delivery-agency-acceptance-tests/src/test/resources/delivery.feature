Feature: Delivery
  
  Scenario: Create delivery
    When the client requests POST
    Then the response must be valid

  Scenario: Get delivery by id
    When the client requests POST
    Then the response must be valid
    When the client requests GET
    Then the response must be valid

  Scenario: Get delivery by invalid id
    When the client requests GET with invalid id
    Then the response should be exception
    Then the response exception status code should be "422"
