package resourceManagers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath = System.getProperty("user.dir") + "//configs//Configurations.properties";
	

	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public String browser() {
		String browser = properties.getProperty("browser");
		if (browser != null)
			return browser;
		else
			throw new RuntimeException("browser not specified in the Configuration.properties file.");
	}
	public String envName="";
	public String getApplicationUrl() {
		String url=null;

		String env=System.getProperty("env");
		if (env==null)
			env=properties.getProperty("environment");

		System.out.println(env);
		envName=env;
		if(env!= null &&(env.equalsIgnoreCase("QA") || env.equalsIgnoreCase("PreProd") || env.equalsIgnoreCase("QA2") || env.equalsIgnoreCase("Dev") || env.equalsIgnoreCase("Prod"))) {
			url = properties.getProperty("url" + env.toUpperCase());
		}

		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	
	
	public String getEnvironmentName() {
		return envName;
	}
	public String getReportConfigPath() {
		String relReportConfigPath = properties.getProperty("relReportConfigPath");
		String reportConfigPath = System.getProperty("user.dir") + relReportConfigPath;
		if (relReportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException("Report Config Path not specified in the Configuration.properties file");
	}

	public String getLoginID() {
		String loginID = properties.getProperty("loginID");
		if (loginID != null)
			return loginID;
		else
			throw new RuntimeException("Login ID is not specified in the Configuration.properties file");
	}
	
	public String getLoginID2() {
		String loginID2 = properties.getProperty("loginID2");
		if (loginID2 != null)
			return loginID2;
		else
			throw new RuntimeException("Login ID2 is not specified in the Configuration.properties file");
	}

	public String getPassword() {
		String password = properties.getProperty("password");
		if (password != null)
			return password;
		else
			throw new RuntimeException("Password is not specified in the Configuration.properties file");
	}

	public String getGGAdminLoginID() {
		String loginID = properties.getProperty("ggAdminLoginID");
		if (loginID != null)
			return loginID;
		else
			throw new RuntimeException("Login ID is not specified in the Configuration.properties file");
	}

	public String getGGAdminPassword() {
		String password = properties.getProperty("ggAdminPassword");
		if (password != null)
			return password;
		else
			throw new RuntimeException("Password is not specified in the Configuration.properties file");
	}

	public String getUserCountryCode() {
		String countryCode = properties.getProperty("loginUserCountryCode");
		if (countryCode != null)
			return countryCode;
		else
			throw new RuntimeException("Country Code is not specified in the Configuration.properties file");
	}

	public String getUserCountry() {
		String country = properties.getProperty("loginUserCountry");
		if (country != null)
			return country;
		else
			throw new RuntimeException("Country is not specified in the Configuration.properties file");
	}

	public String getSelectedCountry() {
		String selectedCountry = properties.getProperty("selectedCountry");
		if (selectedCountry != null)
			return selectedCountry;
		else
			throw new RuntimeException("Selected country is not specified in the Configuration.properties file");
	}

	public String getSelectedCountryCode() {
		String selectedCountryCode = properties.getProperty("selectedCountryCode");
		if (selectedCountryCode != null)
			return selectedCountryCode;
		else
			throw new RuntimeException("Selected country code is not specified in the Configuration.properties file");
	}

	public String getGeoCountryCode() {
		String geoCountryCode = properties.getProperty("geoCountry");
		if (geoCountryCode != null)
			return geoCountryCode;
		else
			throw new RuntimeException("Geo country code is not specified in the Configuration.properties file");
	}

	public String getGG_AdminManagerCookieName() {
		String value = properties.getProperty("ggAdminCookieName");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Cookie Name is not specified in the Configuration.properties file");

	}


	public String getGG_AdminManagerCookieValue() {
		String value = null;
		String env=System.getProperty("env");
		if (env==null)
			env=properties.getProperty("environment");

		System.out.println("ggAdminManagerCookieValue" + env.toUpperCase());
		if(env!= null &&(env.equalsIgnoreCase("QA") || env.equalsIgnoreCase("PreProd") || env.equalsIgnoreCase("QA2") || env.equalsIgnoreCase("Dev") || env.equalsIgnoreCase("Prod"))) {
			value = properties.getProperty("ggAdminManagerCookieValue" + env.toUpperCase());
		}
		if (value != null) {
			return value;
		}
		else
		{
			throw new RuntimeException("Cookie Value is not specified in the Configuration.properties file");
		}
	}
	
	public String getGG_AdminLookUpToolManagerCookieValue() {
		String value=null;

		String env=System.getProperty("env");
		if (env==null)
			env=properties.getProperty("environment");

		//		System.out.println(env);
		if(env!= null &&(env.equalsIgnoreCase("QA") || env.equalsIgnoreCase("PreProd") || env.equalsIgnoreCase("QA2") || env.equalsIgnoreCase("Dev") || env.equalsIgnoreCase("Prod"))) {
			value = properties.getProperty("ggAdminLookUpToolManagerCookieValue" + env.toUpperCase());
		}
		//		String value = properties.getProperty("ggAdminLookUpToolManagerCookieValue");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Cookie Value is not specified in the Configuration.properties file");
	}
	public String getGG_AdminReaderCookieValue() {
		String value=null;

		String env=System.getProperty("env");
		if (env==null)
			env=properties.getProperty("environment");

		//		System.out.println(env);
		if(env!= null &&(env.equalsIgnoreCase("QA") || env.equalsIgnoreCase("PreProd") || env.equalsIgnoreCase("QA2") || env.equalsIgnoreCase("Dev") || env.equalsIgnoreCase("Prod"))) {
			value = properties.getProperty("ggAdminReaderCookieValue" + env.toUpperCase());
		}
		//		String value = properties.getProperty("ggAdminReaderCookieValue");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Cookie Value is not specified in the Configuration.properties file");
	}

	public String getGG_AdminDomain() {
		String value = properties.getProperty("ggAdminDomain");
		if (value != null)
			return value;
		else
			throw new RuntimeException("Cookie domain is not specified in the Configuration.properties file");

	}

	public String getEnvironment() {
		String env=System.getProperty("env");
		if (env==null)
			env=properties.getProperty("environment");
		return env;
	}


	//============================DB Configurations========================================================
	
	public String getGG_DBHostName() {
		String DbHostName="";
		try {
			String env=System.getProperty("env");
			if (env==null)
				env=properties.getProperty("environment");

			System.out.println(env);

			if(env!= null &&(env.equalsIgnoreCase("QA2"))){
				DbHostName = properties.getProperty("ggDBHostNameQA2");
			}
			else if(env!= null &&(env.equalsIgnoreCase("PREPROD"))){
				DbHostName = properties.getProperty("ggDBHostNamePREPROD");
			}
		}
		catch (Exception ex) {
			if (DbHostName == null)
				throw new RuntimeException("DB HostName not specified in the Configuration.properties file.");
		}
		return DbHostName;
	}
	
	public String getGG_DBName() {
		String DbName="";
		try {
			String env=System.getProperty("env");
			if (env==null)
				env=properties.getProperty("environment");

			System.out.println(env);

			if(env!= null &&(env.equalsIgnoreCase("QA2"))){
				DbName = properties.getProperty("ggDBNameQA2");
			}
			else if(env!= null &&(env.equalsIgnoreCase("PREPROD"))){
				DbName = properties.getProperty("ggDBNamePREPROD");
			}
		}
		catch (Exception ex) {
			if (DbName == null)
				throw new RuntimeException("DB Name not specified in the Configuration.properties file.");
		}
		return DbName;
	}
	
	public String getGG_DBUserName() {
		String DbUserName="";
		try {
			String env=System.getProperty("env");
			if (env==null)
				env=properties.getProperty("environment");

			System.out.println(env);

			if(env!= null &&(env.equalsIgnoreCase("QA2"))){
				DbUserName = properties.getProperty("ggDBUserNameQA2");
			}
			else if(env!= null &&(env.equalsIgnoreCase("PREPROD"))){
				DbUserName = properties.getProperty("ggDBUserNamePREPROD");
			}
		}
		catch (Exception ex) {
			if (DbUserName == null)
				throw new RuntimeException("DB UserName not specified in the Configuration.properties file.");
		}
		return DbUserName;
	}
	
	public String getGG_DBPassword() {
		String DbPassword="";
		try {
			String env=System.getProperty("env");
			if (env==null)
				env=properties.getProperty("environment");

			System.out.println(env);

			if(env!= null &&(env.equalsIgnoreCase("QA2"))){
				DbPassword = properties.getProperty("ggDBPasswordQA2");
			}
			else if(env!= null &&(env.equalsIgnoreCase("PREPROD"))){
				DbPassword = properties.getProperty("ggDBPasswordPREPROD");
			}
		}
		catch (Exception ex) {
			if (DbPassword == null)
				throw new RuntimeException("DB Password not specified in the Configuration.properties file.");
		}
		return DbPassword;
	}

}

