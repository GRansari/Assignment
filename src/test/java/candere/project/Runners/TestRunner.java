package candere.project.Runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features="classpath:Features",
		glue="candere.project.StepDefs",
		tags="@SearchProd1",
		plugin = {"pretty", // to generate reports
			       "html:target/html/htmlreport.html",
			       "json:target/json/file.json",
			            },
				    monochrome=true,
			        publish=true,
			        dryRun=false
	
		)




public class TestRunner {

}
