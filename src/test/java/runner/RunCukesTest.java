package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import static cucumber.api.SnippetType.CAMELCASE;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber.json", "html:target/site/cucumber-pretty"}, features = {"src/test/java/features"}, snippets = CAMELCASE, glue={"steps"})

public class RunCukesTest {
}
