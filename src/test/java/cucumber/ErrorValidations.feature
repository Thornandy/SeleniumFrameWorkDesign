@tag
Feature: Error validation

  @ErrorValidation
  Scenario Outline: validate wrong user
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name                   | password |
      | luffymarco@yopmail.com | Lutyy@65 |
