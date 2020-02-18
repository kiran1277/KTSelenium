package pageObjects

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class GoogleHomePage(val driver: WebDriver) {
    @FindBy(linkText = "Sign in")
    var signIn: WebElement? = null

    @FindBy(linkText = "Gmail")
    var gmail: WebElement? = null

    @FindBy(name = "q")
    var searchEdit: WebElement? = null

    @FindBy(xpath = "//input[@value='Google Search']")
    var searchButton: WebElement? = null

    @FindBy(xpath = "//div[text()='All']")
    var allResults: WebElement? = null

    @FindBy(xpath = "//div[@class='g']//a")
    var resultLinks: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }
}