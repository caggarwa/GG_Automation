package runner;

import java.io.File;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import pages.BasePage;
import resourceManagers.CommonUtilities;
import resourceManagers.FileReaderManager;

@CucumberOptions(features = "src/test/resources",
				glue 	  = "stepDefinitions", 
				plugin	  = { "pretty" 
							/*"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
							"json:target/Destination/cucumber.json",
							"rerun:rerun/rerun.txt"	*/
							},
			monochrome   = true,
					tags = { "@Regression" })


public class TestRunner  extends BasePage {
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		try {
			Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
			Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
			Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
			Reporter.setSystemInfo("Machine", System.getProperty("os.name"));
			closeBrowser();

			CommonUtilities.waitForRerunTextFile();
			testNGCucumberRunner.finish();
		}
		catch(Exception e)
		{ 
			System.out.println("There is an exception in one of the runner class: " + e.getMessage());
		}
	}
}
