package libraries

import io.cucumber.java8.Scenario
import junit.framework.TestCase
import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select

open class Utilities : DriverFactory() {
    fun enterTextInEdit(element: WebElement, text: String?) {
        try { //element.clear();
            element.sendKeys(text)
        } catch (e: NoSuchElementException) {
            throw e
        }
    }

    fun switchFrame(element: WebElement?) {
        getDriver!!.switchTo().frame(element)
    }

    val isAlertPresent: Boolean
        get() = try {
            driver!!.switchTo().alert()
            true
        } catch (ex: NoAlertPresentException) {
            throw ex
        }

    fun switchToDefault() {
        driver!!.switchTo().defaultContent()
    }

    fun doubleClickElement(element: WebElement?) {
        val actions = Actions(driver!!)
        actions.moveToElement(element).doubleClick().build().perform()
    }

    fun getAttributeValue(element: WebElement, attribute: String?): String {
        clickElement(element)
        return element.getAttribute(attribute)
    }

    fun selectDropDownListValue(element: WebElement?, listValue: String?) {
        val dropdown = Select(element)
        dropdown.selectByVisibleText(listValue)
        if (!dropdown.firstSelectedOption.text.contains(listValue!!)) TestCase.fail("Unable to select given value" + dropdown.firstSelectedOption.text)
    }

    fun wait(Seconds: Int) {
        try {
            Thread.sleep(Seconds * 1000.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun writeToLog(scenario: Scenario, message: String?) {
        scenario.write(message)
    }

    fun fnScreenshot(scenario: Scenario,driver: WebDriver) {
        try {
            val screenshot = (driver as TakesScreenshot).getScreenshotAs(
                OutputType.BYTES
            )
            scenario.embed(screenshot, "image/png","test")
        } catch (somePlatformsDontSupportScreenshots: WebDriverException) {
            System.err.println(somePlatformsDontSupportScreenshots.message)
        }
    }

    fun verifyText(element: WebElement, text: String?): Boolean {
        return try {
            element.text.equals(text, ignoreCase = true)
        } catch (e: NoSuchElementException) {
            false
        }
    }

    fun isElementDisplayed(element: WebElement): Boolean {
        return try {
            element.isDisplayed
        } catch (e: NoSuchElementException) {
            false
        }
    }

    fun clickElement(element: WebElement) {
        try {
            element.click()
        } catch (e: NoSuchElementException) {
            throw e
            //LogUtil.info("No Such Element Exist");
        }
    }

    fun moveToElement(element: WebElement?) {
        val action = Actions(driver!!)
        try {
            action.moveToElement(element).build().perform()
        } catch (e: NoSuchElementException) {
            throw e
        }
    }

    fun verifyAttributeValue(
        element: WebElement,
        attribute: String?,
        expectedValue: String?
    ): Boolean {
        return try {
            clickElement(element)
            element.getAttribute(attribute).equals(expectedValue, ignoreCase = true)
        } catch (e: NoSuchElementException) {
            false
        }
    }

}