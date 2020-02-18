package stepDefinitions

import libraries.Utilities
import io.cucumber.java8.En

class Stepdefs : En, Utilities() {
    init {
        Given("^I am navigate to google home page$") {
            getPage(System.getProperty("baseURL"))
        }
        When("^I search for \"([^\"]*)\"$") { arg0: String? ->
            enterTextInEdit(googleHomePage?.searchEdit!!, arg0)
            clickElement(googleHomePage?.searchButton!!)
            waitForElementDisplay(googleHomePage?.resultLinks)
        }
        Then("^I verify results$") {
            assert(googleHomePage?.resultLinks!!.isDisplayed)
        }
    }


}