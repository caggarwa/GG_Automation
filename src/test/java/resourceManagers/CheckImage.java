package resourceManagers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import pages.BasePage;

public class CheckImage extends BasePage {

//	public CheckImage(WebDriver driver) {
//		super(driver);
//		}
	public CheckImage() {
		super(DriverConfig.driver);
		}
	public boolean checkImage(String xpathLocator ) throws Exception {
		
		WebElement ImageFile = driver.findElement(By.xpath(xpathLocator));
        
		Object result = ((JavascriptExecutor) driver).executeScript(
				   "return arguments[0].complete && "+
				   "typeof arguments[0].naturalWidth != \"undefined\" && "+
				   "arguments[0].naturalWidth > 0", ImageFile);

				    boolean loaded = false;
				    if (result instanceof Boolean) {
				      loaded = (Boolean) result;
				     			      
				    }
				    return loaded;
	
}
	public boolean checkImage(WebElement element ) throws Exception {
		       
		Object result = ((JavascriptExecutor) driver).executeScript(
				   "return arguments[0].complete && "+
				   "typeof arguments[0].naturalWidth != \"undefined\" && "+
				   "arguments[0].naturalWidth > 0", element);

				    boolean loaded = false;
				    if (result instanceof Boolean) {
				      loaded = (Boolean) result;
				     			      
				    }
				    return loaded;
	
}
}
