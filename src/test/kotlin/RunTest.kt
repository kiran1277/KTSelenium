import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features    = ["classpath:features"],
    glue        = ["stepDefinitions"],
    plugin      = ["json:target/cucumber.json"],
    strict      = true
)
class RunTest