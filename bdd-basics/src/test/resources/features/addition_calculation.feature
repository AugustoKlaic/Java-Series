Feature: Addition is calculated between two numbers
  Scenario: Two numbers addition
    Given operator is 3.0 and operand is 2.0 and operation is "ADDITION"
    When operator is added with operand
    Then result must be 5.0