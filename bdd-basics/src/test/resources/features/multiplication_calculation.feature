Feature: Multiplication is calculated between two numbers
  Scenario: Two numbers multiplication
    Given operator is 3.0 and operand is 2.0 and operation is "MULTIPLICATION"
    When calculation is performed
    Then result must be 6.0

  Scenario: Two negative numbers multiplication
    Given operator is -3.0 and operand is -2.0 and operation is "MULTIPLICATION"
    When calculation is performed
    Then result must be 6.0

  Scenario: One positive number and one negative number
    Given operator is 3.0 and operand is -2.0 and operation is "MULTIPLICATION"
    When calculation is performed
    Then result must be -6.0

  Scenario: One positive number and one negative number
    Given operator is -3.0 and operand is 2.0 and operation is "MULTIPLICATION"
    When calculation is performed
    Then result must be -6.0