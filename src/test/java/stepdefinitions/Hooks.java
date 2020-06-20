package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.imageio.ImageIO;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import pages.BasePage;
import resourceManagers.CommonUtilities;
import resourceManagers.DriverConfig;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class Hooks extends BasePage {

	@Before()
	public void beforeScenarioBegin(Scenario scenario) {
		System.out.println("###############################################################################################");
		System.out.println( scenario.getName());
		System.out.println("###############################################################################################");

	}


	@After()
	public void afterScenarioFinish(Scenario scenario) throws IOException{
		boolean takeSS=true;

		try {

			if (scenario.isFailed()) {


				if (driver.getTitle().contains("502")) {
					takeSS=false;
					scenario.write("Execution failed due to 502 error!!");
				}


				if (takeSS) {
					//Get the network logs
					System.out.println(driver.getCurrentUrl());
					//--			scenario.write(CommonUtilities.printNetworkLogs(scenario.getName()));
					//--			CommonUtilities.printJwtToken();

					scenario.embed(CommonUtilities.takeFullPageScreenShotAsByte(DriverConfig.driver), "image/png");
					Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
							.takeScreenshot(DriverConfig.driver);

					// To save the screenshot in desired location
					Date date= new Date();
					long time = date.getTime();

					Timestamp tsp = new Timestamp(time);
					String TimeStamp=(tsp.toString().replace("-", ".")).replace(":", ".");//2015-11-13 13:50:21.007
					String screenshotName = (scenario.getName().replaceAll(" ", "_") + TimeStamp).replaceAll(" ", "_");
					File destinationPath = new File(System.getProperty("user.dir") + "\\target\\cucumber-reports\\screenshots\\" + screenshotName + ".png"	);
					ImageIO.write(screenshot.getImage(), "PNG", destinationPath);
					Reporter.addScreenCaptureFromPath(destinationPath.toString());

				}
			} 

		}
		catch (Exception e) {
			System.out.println("Pointer moves to catch block " + e.getMessage() );
		}
		finally {
			closeBrowser();
		}

	}	
}
