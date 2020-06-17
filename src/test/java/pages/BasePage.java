
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resourceManagers.CommonUtilities;

import resourceManagers.ConfigFileReader;

public class BasePage {
	public static WebDriver driver;
	public ConfigFileReader configFileReader;

	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
	}

	public BasePage(){

	}

	
	/** 
	* This method navigates to the homepage of geneglobe 
	* as per the environment specified and accepts cookie disclaimer
	* @param NA
	* @return null
	* @throws InterruptedException
	*/
	public void navigateToHomePage() throws InterruptedException {		
		configFileReader = new ConfigFileReader();
		driver.manage().deleteAllCookies();
//		CommonUtilities.configureWait("normalwait", 3);
		driver.get(configFileReader.getApplicationUrl());
//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		waitForGeneGlobeHomePage(10);
		CommonUtilities.acceptCookies();
	}

	/** 
	* This method logs in and accept cookie disclaimer
	* @param NA
	* @return null
	*/
	public void loginToGG() {
		CommonUtilities.configureWait("normalwait", 2);
		if(driver.getCurrentUrl().contains("login")) {
			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(configFileReader.getLoginID());
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(configFileReader.getPassword());
			driver.findElement(By.xpath("//*[@id=\"fm1\"]/section[4]/input[4]")).click();
			CommonUtilities.acceptCookies();
		}
	}	

	
	public void waitForGeneGlobeHomePage(int waitInSec) {
		boolean conditionTrue=false;
		for (int iwait=0; iwait<waitInSec; iwait++) {
			String title="Gene";
			conditionTrue=driver.getTitle().contains(title); //Constants.geneGlobePageTitle
			if (!conditionTrue) {
				CommonUtilities.configureWait("normalwait", 1);
			}
			else  {
				break;
			}
		}
	}

	public void closeBrowser() {

		try {
			driver.quit();
		}
		catch(Exception ex) {

		}
	}
}
