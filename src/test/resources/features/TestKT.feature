Feature: Testing selenium from Kotlin
  Scenario: first kotlin test
    Given I am navigate to google home page
    When I search for "Kotlin"
    Then I verify results
