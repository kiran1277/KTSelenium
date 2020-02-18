package stepDefinitions

import libraries.Utilities
import io.cucumber.java8.En


class Hooks : En, Utilities() {
    init {
        Before { scenario ->
            Setup(System.getProperty("browser"))
            writeToLog(scenario,"stared testing")
        }
        After { scenario ->
            writeToLog(scenario,"Current Page URL is " + driver!!.getCurrentUrl())
            fnScreenshot(scenario,driver!!)
            tearDown()
        }
    }



}
