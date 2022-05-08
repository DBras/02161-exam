package plannerapp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/* Important:
This class was copied from the Library App solution. This runs all tests.
*/

@RunWith(Cucumber.class)
@CucumberOptions(plugin="summary"
        ,features={"features"}
        ,publish= false
)
public class CucumberTest {
}
