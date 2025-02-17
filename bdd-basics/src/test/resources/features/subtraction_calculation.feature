Feature: Subtraction is calculated between two numbers
  Scenario: Two numbers subtraction
    Given operator is 3.0 and operand is 2.0 and operation is "SUBTRACTION"
    When operator is subtracted with operand
    Then result must be 1.0

  Scenario: Two negative numbers subtraction
    Given operator is -3.0 and operand is -2.0 and operation is "SUBTRACTION"
    When operator is subtracted with operand
    Then result must be -1.0

  Scenario: One positive number and one negative number
    Given operator is 3.0 and operand is -2.0 and operation is "SUBTRACTION"
    When operator is subtracted with operand
    Then result must be 5.0

  Scenario: One positive number and one negative number
    Given operator is -3.0 and operand is 2.0 and operation is "SUBTRACTION"
    When operator is subtracted with operand
    Then result must be -5.0