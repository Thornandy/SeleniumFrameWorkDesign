@tag
Feature: Purchase the order from Ecommerce website

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples:
      | name                   | password | productName |
      | luffymarco@yopmail.com | Luffy@65 | ZARA COAT 3 |
