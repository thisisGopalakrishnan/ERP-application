package runnerclass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ="src\\test\\resources\\Featurefile",
glue={"org.stepdefination"} ,
monochrome = true
)



public class Runnerclass extends AbstractTestNGCucumberTests{
	@Override //method scenaio has override
	
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	
	
}
