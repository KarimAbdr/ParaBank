@ui @bdd
Feature: PB-1 Customer login
  As a ParaBank customer
  I want to log in with my username and password
  So that I can see my accounts

  Background:
    Given the customer is on the ParaBank home page

  Scenario: Valid credentials open Accounts Overview
    When the customer logs in as "john" with password "demo"
    Then the page title is "Accounts Overview"
    And the customer is greeted as "John Smith"

  # TODO 8 (bonus): remove the @todo tag and write the missing step definition in LoginSteps
  @todo
  Scenario Outline: Invalid credentials are rejected
    When the customer logs in as "<username>" with password "<password>"
    Then the error message "The username and password could not be verified." is shown

    Examples:
      | username | password |
      | john     | wrong    |
      | nobody   | demo     |
