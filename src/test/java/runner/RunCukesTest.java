package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import static cucumber.api.SnippetType.CAMELCASE;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"}, snippets = CAMELCASE, glue={"steps"})

public class RunCukesTest {
}
