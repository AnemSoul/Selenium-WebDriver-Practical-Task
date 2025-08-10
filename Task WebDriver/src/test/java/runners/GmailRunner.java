package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"steps"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    tags = "@smoke" // You can change this to your desired tag
)
public class GmailRunner extends AbstractTestNGCucumberTests {
}