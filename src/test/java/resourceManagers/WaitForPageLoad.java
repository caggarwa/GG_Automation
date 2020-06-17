package resourceManagers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForPageLoad {

	             public void pageLoadWait (WebDriver driver, int timeout) {
	                         ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
	                         public Boolean apply(WebDriver driver) {
	                                      return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	                          }
	                };

	                WebDriverWait wait = new WebDriverWait(driver, timeout);
	                wait.until(condition);
	          }
}
