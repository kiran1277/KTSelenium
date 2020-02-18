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
//                    driver!!.findElement(By.name("q")).sendKeys(arg0)
//                    driver!!.findElement(By.name("btnK")).click()
            clickElement(googleHomePage?.searchButton!!)
            wait(10)
        }
        Then("^I verify results$") {
            assert(googleHomePage?.resultLinks!!.isDisplayed)
        }
    }


}