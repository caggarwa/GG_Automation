package resourceManagers;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverConfig {

	public static  WebDriver driver;

	static  ConfigFileReader configFileReader;


	public static void driverInit() {

		System.err.println(System.getProperty("os.name"));

		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			configFileReader= new ConfigFileReader();
			//				if(configFileReader.browser().equalsIgnoreCase("ChromeHeadless")) { 

			LoggingPreferences preferences = new LoggingPreferences();
			preferences.enable(LogType.PERFORMANCE, Level.ALL);

			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");	
			String downloadFilepath = System.getProperty("user.dir")+File.separator+"Downloads";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("download.prompt_for_download", false);
			chromePrefs.put("download.directory_upgrade", true);
			chromePrefs.put("profile.default_content_settings.popups", 0);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--incognito");
			options.setCapability(CapabilityType.LOGGING_PREFS, preferences ); //for Network traffic
			//					options.setCapability( "goog:loggingPrefs", preferences ); // For new Chrome
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("applicationCacheEnabled", false);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.addArguments("headless");
			options.addArguments("window-size=1980x1080");
			driver = new ChromeDriver(options);	
			
		}
		else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			configFileReader= new ConfigFileReader();
			if(configFileReader.browser().equalsIgnoreCase("FireFox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.home")+"\\Drivers\\geckodriver-v0.21.0-win64\\geckodriver.exe");
				driver= new FirefoxDriver();

			}
			else if(configFileReader.browser().equalsIgnoreCase("Chrome")) {
				//for Network traffic
				LoggingPreferences preferences = new LoggingPreferences();
				preferences.enable(LogType.PERFORMANCE, Level.INFO);
				System.out.println("user Directory : "+System.getProperty("user.dir"));
				String downloadFilepath = System.getProperty("user.dir")+"\\Downloads";
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilepath);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--incognito");
				options.setCapability(CapabilityType.LOGGING_PREFS, preferences ); //for Network traffic
				//					options.setCapability( "goog:loggingPrefs", preferences ); // For new Chrome
				options.addArguments("--ignore-ssl-errors=yes");
				options.addArguments("--ignore-certificate-errors");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("applicationCacheEnabled", false);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				System. setProperty("webdriver.chrome.driver", System.getProperty("user.home")+"\\Drivers\\chromedriver_win32\\chromedriver.exe" );    
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
			}
			else if(configFileReader.browser().equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.home")+"\\Drivers\\IEDriverServer_x64_3.4.0\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			else if(configFileReader.browser().equalsIgnoreCase("ChromeHeadless")) { 
				//for Network traffic
				LoggingPreferences preferences = new LoggingPreferences();
				preferences.enable(LogType.PERFORMANCE, Level.ALL);

				String downloadFilepath = System.getProperty("user.dir")+"\\Downloads";
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilepath);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("headless");
				options.addArguments("--incognito");
				options.addArguments("window-size=1200x600");
				options.setCapability(CapabilityType.LOGGING_PREFS, preferences ); //for Network traffic
				//					options.setCapability( "goog:loggingPrefs", preferences ); // For new Chrome
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("applicationCacheEnabled", false);
				//					capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.home")+"\\Drivers\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver(options);	
				}
		}
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
	}
	
}
