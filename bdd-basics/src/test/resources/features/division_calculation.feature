Feature: Division is calculated between two numbers
  Scenario: Two numbers multiplication
    Given operator is 4.0 and operand is 2.0 and operation is "DIVISION"
    When calculation is performed
    Then result must be 2.0

  Scenario: Two negative numbers multiplication
    Given operator is -4.0 and operand is -2.0 and operation is "DIVISION"
    When calculation is performed
    Then result must be 2.0

  Scenario: One positive number and one negative number
    Given operator is 4.0 and operand is -2.0 and operation is "DIVISION"
    When calculation is performed
    Then result must be -2.0

  Scenario: One positive number and one negative number
    Given operator is -4.0 and operand is 2.0 and operation is "DIVISION"
    When calculation is performed
    Then result must be -2.0

  Scenario: Division by zero
    Given operator is 2.0 and operand is 0.0 and operation is "DIVISION"
    When calculation is performed
    Then an exception is thrown with message "zero division not permitted"