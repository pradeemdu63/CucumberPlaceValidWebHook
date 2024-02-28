package cucumberRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions" },
plugin = {"json:target/jsonReport/cucumber-report.json",
"html:target/cucumber-report/cucumber.html"})
//, tags = "@DeletePlace")
public class TestRunner {

}
