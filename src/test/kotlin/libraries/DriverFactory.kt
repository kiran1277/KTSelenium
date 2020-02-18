package libraries

import pageObjects.GoogleHomePage
import io.github.bonigarcia.wdm.*
import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.concurrent.TimeUnit


//import org.openqa.selenium.phantomjs.PhantomJSDriver;
open class DriverFactory {
    protected fun Setup(browser: String) {
        InitiateDriver(browser)
        InitializePages()
//        return driver;
    }
    private fun InitiateDriver(browser: String) { //String os = System.getProperty("os.name");
        val caps = DesiredCapabilities()
        when (browser) {
            "chrome" -> {
                ChromeDriverManager.chromedriver().setup()
                driver = ChromeDriver()
            }
            "firefox" -> {
                FirefoxDriverManager.firefoxdriver().setup()
                driver = FirefoxDriver()
            }
            "ie" -> {
                caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true)
                caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true)
                InternetExplorerDriverManager.iedriver().setup()
                driver = InternetExplorerDriver()
            }
            "phantom" -> {
                PhantomJsDriverManager.phantomjs().setup()
                caps.setCapability("takesScreenshot", true)
                caps.isJavascriptEnabled = true
            }
            "edge" -> {
                EdgeDriverManager.edgedriver().setup()
                driver = EdgeDriver()
            }
            else -> Assert.fail("Unknown browser")
        }
        driver!!.manage().window().maximize()
        driver!!.manage().timeouts()
            .implicitlyWait(30, TimeUnit.SECONDS)
    }

    fun getPage(url: String?) {
        driver!![url]
    }

    fun InitializePages() {
        googleHomePage = GoogleHomePage(driver!!)
    }

    val pageTitle: String
        get() = driver!!.title

    val pageURL: String
        get() = driver!!.currentUrl

    val getDriver: WebDriver?
        get() = driver
    fun tearDown() {
        driver!!.quit()
    }

    companion object {
        var driver: WebDriver? = null
            private set
        var googleHomePage:GoogleHomePage? = null
    }
}