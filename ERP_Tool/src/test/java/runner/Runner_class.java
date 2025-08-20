package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src\\test\\resources\\Feature_File\\Excel_comparision.feature",
		glue= {"org.stepdefination"},
		plugin= {"html:Reports/cucumber.report.html"},
		monochrome = true
		)

public class Runner_class extends AbstractTestNGCucumberTests {
	
	 @DataProvider(parallel = true) // ✅ Enables parallel execution
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }


}
