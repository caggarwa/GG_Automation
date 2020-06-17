package resourceManagers;

import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.io.Reader;
import javax.imageio.ImageIO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CommonUtilities extends BasePage {


	public enum WaitType 
	{ 
		Clickable, Visibility, Selectable; 
	} 

	//	public CommonUtilities(WebDriver driver) {
	//		super(driver);
	//		}
	public CommonUtilities() {
		super(DriverConfig.driver);
	}
	//static JavascriptExecutor executor = (JavascriptExecutor)driver;
	//static Actions builder = new Actions(driver);


	public static void driverQuit() {
		driver.quit();
	}

	public static boolean isClickable(WebElement webe)      
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(webe));
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/*
	 * Author:Karthik.Kumars@contractor.qiagen.com
	 * Objective:This function will scroll horizontally/vertically
	 * Parameters
	 * 		Input:int x,int y
	 * 	Return Value:NA
	 */
	public static void scrollXY(int x,int y) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("window.scrollBy("+x+","+y+")", "");
	}
	/*
	 * Author:Jithin.James@contractor.qiagen.com
	 * Objective:This function will scroll into element view
	 * Parameters
	 * 		Input:WebElement element
	 * Return Value:NA
	 */
	public static void scrollIntoView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/*
	 * Author:Karthik.Kumars@contractor.qiagen.com
	 * Objective:This function will configure multiple explicit wait methods
	 * Parameters
	 * 		Input:String waitType, WebElement element,int time
	 * 	Return Value:NA
	 */
	public static void configureExplicitWait(String waitType, WebElement element,int time) {
		//System.out.println("custom wait exectution---------------------------------------------------- "+time);
		if(waitType.equalsIgnoreCase("waitforclickable")) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		else if(waitType.equalsIgnoreCase("waitforvisibility")) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));

		}
		else if(waitType.equalsIgnoreCase("waitforselect")) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeSelected(element));
		}
		else if(waitType.equalsIgnoreCase("waitforinvisibility")) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.invisibilityOf(element));
		}
	}

	/*
	 * Author:Karthik.Kumars@contractor.qiagen.com
	 * Objective:This function will configure wait methods  without passing elements
	 * Parameters
	 * 		Input:String waitType,int time
	 * 	Return Value:NA
	 */
	public static void configureWait(String waitType, int time) {
		try
		{
			if(waitType.equalsIgnoreCase("implicitwait")) {
				driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			}
			else if(waitType.equalsIgnoreCase("alertpresent")) {
				WebDriverWait wait = new WebDriverWait(driver, time);
				wait.until(ExpectedConditions.alertIsPresent());
			}	
			else if(waitType.equalsIgnoreCase("normalwait")) {
				Thread.sleep((time*1000));
			}
		}
		catch(Exception e) {
			System.out.println("Error Occured in wait method"+e);
		}
	}

	/*
	 * Author:Jithin.James@contractor.qiagen.com
	 * Objective:This function will create random numbers between 0 & 1000
	 * Parameters
	 * 		Input:Na
	 * 	Return Value:randomNumber
	 */
	public static int randomNumberGenerator() {
		Random randomNumberGenerator = new Random();
		int randomNumber = randomNumberGenerator.nextInt(1000);
		return(randomNumber);
	}

	/*
	 * Author:Chetanya.Aggarwal@contractor.qiagen.com
	 * Objective:This function will create random numbers on basis of time stamp
	 * Parameters
	 * 		Input:Na
	 * 	Return Value:randomNumber
	 */	
	public static String randomNumber(int Length){

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String TimeStamp=String.valueOf(timestamp.getTime());
		return TimeStamp.substring(TimeStamp.length()-Length);		
	}




	// to select an item from dropdown with name
	public static void selectbyText(WebElement element,String option)      
	{
		try
		{
			Select dropdown = new Select(element);
			configureWait("normalwait",3);
			dropdown.selectByVisibleText(option);

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	// to select an item from dropdown with name
	public static void selectbyValue(WebElement element,String option)      
	{
		try
		{
			Select dropdown = new Select(element);
			configureWait("normalwait",3);
			dropdown.selectByValue(option);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	// to select an item from dropdown
	public static void selectbyIndex(WebElement element,int index)      
	{
		try
		{
			Select dropdown = new Select(element);
			configureWait("normalwait",3);
			dropdown.selectByIndex(index);

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	/*
	 * Author:Jithin.James@contractor.qiagen.com
	 * Objective:This function will click on element using javascript executor
	 * Parameters
	 * 		Input:Na
	 * 	Return Value:randomNumber
	 */
	public static void forceClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	/*
	 * Author:Jithin.James@contractor.qiagen.com
	 * Objective:This function will click on element using action class
	 * Parameters
	 * 		Input:NA
	 * 	Return Value:NA
	 */
	public static void moveToElementClick(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().build().perform();
	}

	/*
	 * Author:Jithin.James@contractor.qiagen.com
	 * Objective:This function will create new tab in browser
	 * Parameters
	 * 		Input:NA
	 * 	Return Value:NA
	 */
	public static void openNewTab() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("window.open()");
	}

	/*
	 * Author:Jithin.James@contractor.qiagen.com
	 * Objective:This function will switch tab in browser
	 * Parameters
	 * 		Input:NA
	 * 	Return Value:NA
	 */
	public static void switchToTab() {
		for(String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}
	}

	public static void switchToNewlyOpenedTab() {
		Set<String> tabs = driver.getWindowHandles();
		System.out.println(tabs.size());
		//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		//		System.out.println(tabs.size());
		for(String tab: tabs) {
			driver.switchTo().window(tab);
		}
		//		System.out.println(tabs.get(tabs.size()));
		//		System.out.println(tabs.get(tabs.size()-1));
		//		driver.switchTo().window(tabs.get(tabs.size()));
		configureWait("normalwait", 10);
	}

	/*
	 * Author:divya.j@contractor.qiagen.com
	 * Objective:This function will verify file reading
	 * Input: NA
	 * Return Value:String dataFromFile
	 */
	public static String readFileAsString(String fileName)throws Exception 
	{ 
		String dataFromFile = ""; 
		dataFromFile = new String(Files.readAllBytes(Paths.get(fileName))); 
		return dataFromFile; 
	} 

	/*
	 * Author:divya.j@contractor.qiagen.com
	 * Objective:This function will drag and drop elements
	 * Input: WebElement source,WebElement destination
	 * Return Value:NA
	 */
	public static void dragAnddrop(WebElement source,WebElement destination) {	
		try {
			String propertyFilePath= System.getProperty("user.dir") + "/configs/dragAndDrop.js";
			String dataRead;
			dataRead = readFileAsString(propertyFilePath);
			dataRead += "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript(dataRead, source, destination);
			configureWait("normalwait", 5);
		} catch (Exception e) {
			assertTrue(true,"Drag and Drop failed");
		}
	}


	/*
	 * Author:Akhila.Venugopal@contractor.qiagen.com
	 * Objective:This function will verify dropdown options
	 * Parameters
	 * 		Input:NA
	 * 	Return Value:NA
	 */
	/*	public static void verifyDropdownOptions(String[] expectedValues, WebElement element) {

		Select select = new Select(element); 
		List<WebElement> options = select.getOptions();  
		if(expectedValues.length!=options.size()) {
			assertTrue(false,"Not matched");
		}

		for (int i=0,j=0; i<expectedValues.length&&j<options.size(); i++,j++)
		{
			if (options.get(j).getText().equals(expectedValues[i]))
			{
				assertTrue(true,"Matched");
			}
			else 
			{
				assertTrue(false,"Not matched");
			}                          
		} 

	}*/

	public static void verifyDropdownOptions(String[] expectedValues, WebElement element) {

		Select select = new Select(element); 
		List<WebElement> actualDropdownValues = select.getOptions(); 
		System.out.println("SelectOption:"+actualDropdownValues);
		boolean comparisonOfActualExpectedDropdownValues = false;
		if (expectedValues.length == actualDropdownValues.size()) {
			for(WebElement dropdownVal:actualDropdownValues) {
				for(int i=0;i<expectedValues.length;i++) {
					System.out.println("Expected:"+expectedValues[i]);
					System.out.println("Actual:"+(actualDropdownValues.get(i)).getText());
					comparisonOfActualExpectedDropdownValues=dropdownVal.getText().equals(expectedValues[i]);
					if (comparisonOfActualExpectedDropdownValues) {
						break;
					}
				}
				assertTrue(comparisonOfActualExpectedDropdownValues,"Actual & Exepcted are not matched");

			}

		}
		else {
			assertTrue(comparisonOfActualExpectedDropdownValues,"Actual & Exepcted number of items to be compared are not equal");
		}
	}
	
	public static void verifyDropdownValues(String[] expectedValues, WebElement element) {

		Select select = new Select(element); 
		List<WebElement> actualDropdownValues = select.getOptions(); 
				for(int i=0;i<expectedValues.length;i++) {
					assertTrue(expectedValues[i].equals(actualDropdownValues.get(i).getText().trim()),"Actual & Exepcted are not matched");
				}
				
	}

	/*

	 * Author:Karthik.Kumars@contractor.qiagen.com
	 * Objective:This function will Check the file from a specific directory with extension
	 * Parameters
	 * 		Input:String waitType,int time
	 * 	Return Value:NA
	 */

	public static boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
		System.out.println("Extension "+dirPath);
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			flag = false;
		}

		for (int i = 1; i < files.length; i++) {
			if(files[i].getName().contains(ext)) {
				flag=true;
			}
		}
		return flag;
	}

	public static ArrayList<String> ExcelReader(String SAMPLE_XLSX_FILE_PATH) throws EncryptedDocumentException, InvalidFormatException, IOException {

		ArrayList<String> excelData=new ArrayList<String>();
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Or you can use a for-each loop to iterate over the rows and columns
		System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
		for (Row row: sheet) {
			for(Cell cell: row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				cellValue=cellValue.trim();
				excelData.add(cellValue);
				System.out.print(cellValue + "\t");
			}
			System.out.println();
		}
		return excelData;

	}
	
	public static Map<String,String> excelReaderHashMap(String filePath,String sheetName) throws IOException, EncryptedDocumentException, InvalidFormatException {

		  Workbook workbook = WorkbookFactory.create(new File(filePath));
		
		  Sheet sheet = workbook.getSheet(sheetName);
		  DataFormatter dataFormatter = new DataFormatter();
		  
		  int lastRow = sheet.getLastRowNum();
		  
		  Map<String, String> excelDataMap = new HashMap<String, String>();
		  
		  
		  for(int i=0; i<=lastRow; i++){
			  Row row = sheet.getRow(i);
			  String keyFormat = dataFormatter.formatCellValue(row.getCell(0));
	          String valueFormat =  dataFormatter.formatCellValue(row.getCell(1));
			  
			  String value = valueFormat.trim();
			  String key = keyFormat.trim();
				  
			  excelDataMap.put(key,value);
		  }
		return excelDataMap;
	}

	public static ArrayList<String> CSVReader(String SAMPLE_CSV_FILE_PATH){
		ArrayList<String> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(SAMPLE_CSV_FILE_PATH))) {
			String line;
			while ((line = br.readLine()) != null) {
				//String[] values = line.split(",");
				//records.addAll(values);
				//System.out.println("value "+line);
				records.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}

	/* Author:Akhila.Venugopal@contractor.qiagen.com
	 * Objective:This function will generate random characters
	 * Parameters
	 * 		Input:NA
	 * 	Return Value:NA
	 */
	public static String generateRandomChars(String candidateChars, int length) {
		StringBuilder stringbuild = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			stringbuild.append(candidateChars.charAt(random.nextInt(candidateChars
					.length())));
		}

		return stringbuild.toString();
	}

	/* Author:Chetanya.Aggarwal@contractor.qiagen.com
	 * Objective:This function will return list of all the Items in Dropdown 
	 * Parameters
	 * 		Input:WebElement
	 * 	Return Value:List<WebElement>
	 */
	public static List<WebElement> getAllItemsInDropdown(WebElement selectElement){
		Select select = new Select(selectElement);
		List<WebElement> allOptions = select.getOptions();
		return allOptions;

	}

	/* Author:Chetanya.Aggarwal@contractor.qiagen.com
	 * Objective:This function will return bumber of all the Items in Dropdown 
	 * Parameters
	 * 		Input:WebElement
	 * 	Return Value:int
	 */
	public static int getSizeOfDropdown(WebElement selectElement){
		int size=0;
		Select select = new Select(selectElement); 
		List<WebElement> options = select.getOptions();  
		size=options.size();

		return size;

	}

	/* Author:Chetanya.Aggarwal@contractor.qiagen.com
	 * Objective:This function will Close all the crome and Chrome Drivers
	 * Parameters
	 * 		Input:NA
	 * 	Return Value:NA
	 */
	public static void closeChrome(){
		try {
			//			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			Runtime.getRuntime().exec("taskkill /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		}
		catch(Exception ex) {

		}

	}

	public static String extractNumberFromString(String sText){
		String sNumber="";
		try{
			StringBuilder myNumbers = new StringBuilder();
			for (int i = 0; i < sText.length(); i++) {
				if (Character.isDigit(sText.charAt(i))) 
					myNumbers.append(sText.charAt(i));

			}
			System.out.println("Your numbers: " + myNumbers.toString());
			sNumber=myNumbers.toString();
		}
		catch(Exception ex){

		}
		return sNumber;
	}

	/**
	 * Writes content to a file.
	 * <p>Content is provided as a single string, therefore use of this 
	 * method is not suggested for large amounts of data.
	 * @param filepath  the filename to create or append to
	 * @param content   the content as a String
	 * @param mode      {@code 'a'} for append or {@code 'w'} to overwrite if exists
	 */
	public static void writeTextFile(String fileName, String content, char mode) {		

		String filePath = getAbsolutePath(fileName);
		FileWriter fWriter = null;
		BufferedWriter bufWrite = null;

		try {
			if (mode == 'w')
				fWriter = new FileWriter(filePath);
			if (mode == 'a')
				fWriter = new FileWriter(filePath, true);
			bufWrite = new BufferedWriter(fWriter);
			bufWrite.write(content);
		} catch (IOException ex) {

		} finally {
			try {
				if (bufWrite != null) {
					bufWrite.flush();
					bufWrite.close();
				}
			} catch (IOException ex) {

			}
		}
	}

	/**
	 * Given a relative path resolves the absolute path.
	 * 
	 * @param filename  the file with relative page to resolve
	 * @return          the absolute path to a file (including file)
	 */
	public static String getAbsolutePath(String filename) {
		File file = new File(filename);
		String filePath = filename;
		try {
			filePath = file.getCanonicalPath();
		} catch (Exception e) {

		}
		return filePath;
	}	


	/**
	 * Return a WebElement that is Under Shadow Root .
	 * 
	 * @param ShadowRootElement  Root WebELement of Shadow Root
	 * @return          the Shadow root WebElement
	 */

	public static WebElement expandShadowRootElement(WebElement ShadowRootElement) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot",ShadowRootElement);
		return ele;
	}


	/**
	 * Return a WebElement that is Under Shadow Root .
	 * 
	 * @param ShadowRootElement  Root WebELement of Shadow Root
	 * @param CssSelector  CSS Locator for the required WebElement
	 * @return          the Required WebElement
	 */
	public static WebElement findElementByXpathShadow(WebElement ShadowRootElement,String CssSelector) {
		WebElement ele=expandShadowRootElement(ShadowRootElement);
		return ele.findElement(By.cssSelector(CssSelector));
	}

	public static WebElement findChildElement(WebElement parentWebElement, String Xpath) {
		WebElement ele=null;
		try {
			ele=parentWebElement.findElement(By.xpath(Xpath));
		}
		catch(Exception ex) {

		}
		return ele;

	}

	public static List<WebElement> findChildElements(WebElement parentWebElement, String Xpath) {
		List<WebElement> ele=null;
		try {
			ele=parentWebElement.findElements(By.xpath(Xpath));
		}
		catch(Exception ex) {

		}
		return ele;

	}

	/*
	 * Author:Chetanya.Aggarwal@contractor.qiagen.com
	 * Objective:This function will configure multiple explicit wait methods
	 * Parameters
	 * 		Input:waitType, WebElement element,int time
	 * 	Return Value:NA
	 */
	public static void configureExplicitWait(WaitType waitType, WebElement element,int time) {
		//System.out.println("custom wait exectution---------------------------------------------------- "+time);
		if(waitType.equals(WaitType.Clickable)){
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		else if(waitType.equals(WaitType.Visibility)) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));

		}
		else if(waitType.equals(WaitType.Selectable)) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeSelected(element));
		}
	}
	
	/**
	 * Perform dynamic wait
	 * @param element
	 * @param time
	 */
	public static void dynamicWait(WebElement element,int time) {
		int n=1;
		String TagName=getTagName(element);
		
		while(TagName=="" && n<time) {
			n+=1;
			configureWait("normalwait", 1);
		}
	}

	public static boolean isVisible(WebElement webe, int waitTimeInSec)      
	{
		try
		{
			
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSec);
			wait.until(ExpectedConditions.visibilityOf(webe));
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}


	public static void acceptCookies() {
		try {
			
			WebElement acceptCookieBtn=driver.findElement(By.xpath("//app-header-cookie-disclaimer//a"));
			if (acceptCookieBtn.isEnabled()) {
				acceptCookieBtn.click();
			}
		}
		catch(Exception e) {
		}
	}

	public static void ctrlPressMouseClick(WebElement Ele) {
		Actions action=new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform();
		Ele.click();
		action.keyUp(Keys.CONTROL).build().perform();
	}

	public static byte[] takeFullPageScreenShotAsByte(WebDriver webDriver) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(DriverConfig.driver);
			BufferedImage originalImage = fpScreenshot.getImage();
			ImageIO.write(originalImage, "png", baos);
			baos.flush();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return baos.toByteArray();
	}


	public static void performMouseHover(WebElement ele) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
	}

	public static void printJwtToken() {
		configureWait("normalwait",3);
		Cookie cookie = driver.manage().getCookieNamed("jwt");
		if (cookie!=null) {
			String encodedToken  = cookie.getValue();
			System.out.println("JWT Token : " + encodedToken);
		}
		else
			System.out.println("JWT token is null");
	}


	public static void generateNetworkLogs(String LogFilePath,  String ScenarioName)  {
		try {
			createLogFile(LogFilePath);
			OutputStream logfile = new FileOutputStream(new File(LogFilePath),true);
			PrintStream printlog = new PrintStream(logfile);
			LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);
			System.out.println(logs);
			printlog.append("**********************************************************************************");
			printlog.append(System.getProperty("line.separator"));
			printlog.append(ScenarioName);
			printlog.append(System.getProperty("line.separator"));
			printlog.append("**********************************************************************************");
			printlog.append(System.getProperty("line.separator"));
			
			for (LogEntry entry : logs) {
				if(entry.toString().contains("\"type\":\"XHR\"") ) { //& entry.toString().contains("url")
					printlog.append(new Date(entry.getTimestamp()) + " " + entry.toString() +" "+ System.getProperty("line.separator"));
					printlog.append(System.getProperty("line.separator"));
					printlog.append(System.getProperty("line.separator"));
				}
			}
			printlog.close();
		}
		catch(Exception ex) {
			ex.getMessage();
		}
	}

	public static void createLogFile(String LogFilePath) {
		File logFile = new File(LogFilePath);
		try {
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
		}
		catch(Exception ex) {
			
		}
	}
	
	
	public static String printNetworkLogs(String ScenarioName)  {
		String sLogs="";
		try {
			
			System.err.println("**********************************************************************************");
			System.err.println("Network Logs for :" +ScenarioName);
			System.err.println("**********************************************************************************");
			
			LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);
			for (LogEntry entry : logs) {
				if(entry.toString().contains("\"type\":\"XHR\"") ) { //& entry.toString().contains("url")
					sLogs=sLogs + (new Date(entry.getTimestamp()) + " " + entry.toString() +" "+ System.getProperty("line.separator"));
//					
//					System.err.println(new Date(entry.getTimestamp()) + " " + entry.toString() +" "+ System.getProperty("line.separator"));
//					System.err.println(System.getProperty(" "));
//					System.err.println(System.getProperty(" "));
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return sLogs;
	}
	
	/* Author:Chetanya.Aggarwal@contractor.qiagen.com
	 * Objective:This function will return bumber of all the Items in Dropdown 
	 * Parameters
	 * 		Input:WebElement
	 * 	Return Value:int
	 */
	public static String getSelectedValueofDropdown(WebElement selectElement){
		WebElement Ele_value;
		Select select = new Select(selectElement); 
		Ele_value=select.getFirstSelectedOption();
		String value=Ele_value.getAttribute("value");
		return value;
	}

	
	public static int getNumberOfInputs(String fileName) throws IOException {
		String filePath=null;
		int inputCount = 0;
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			filePath=System.getProperty("user.dir")+"//Data//"+fileName;
		}
		else if(System.getProperty("os.name").toLowerCase().contains("windows")) {
			filePath=System.getProperty("user.dir")+"\\Data\\"+fileName;
		}
		
		if (fileName.contains("xls")) {
		    File src=new File(filePath);
		    FileInputStream path=new FileInputStream(src);
		    HSSFWorkbook wb= new HSSFWorkbook(path);
		    HSSFSheet workSheet = wb.getSheetAt(0);
		    inputCount = workSheet.getPhysicalNumberOfRows();
			}
		
		else if(fileName.contains("csv")) {
			 Reader reader = Files.newBufferedReader(Paths.get(filePath));
	         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
	         
	         List<CSVRecord> csvRecords = csvParser.getRecords();
	         inputCount=csvRecords.size();
		}
		return inputCount;
	}
	
	public static String getSelectedTextofDropdown(WebElement selectElement){
		WebElement Ele_value;
		Select select = new Select(selectElement); 
		Ele_value=select.getFirstSelectedOption();
		String value=Ele_value.getText();
		return value;
	}
	
	public static ArrayList<String> removeDuplicates(ArrayList<String> list){
		Set<String> set = new LinkedHashSet<>();
		set.addAll(list); 
		list.clear();
		list.addAll(set);
		return list;
	}
	
	
	public static<T> List[] partition(List<T> list, int n)
	{
		// get size of the list
		int size = list.size();

		// calculate number of partitions --> m sublists each of size n
		int m = size / n;
		if (size % n != 0)
			m++;

		// create m empty lists
		List<T>[] partition = new ArrayList[m];
		for (int i = 0; i < m; i++)
		{
			int fromIndex = i*n;
			int toIndex = (i*n + n < size) ? (i*n + n) : size;

			partition[i] = new ArrayList(list.subList(fromIndex, toIndex));
		}

		// return the lists
		return partition;
	}
	
	public static void waitForThreadsToComplete(ExecutorService executor, int numberOfThreads,List<Future<List<Map<String, String>>>> list_Future ){
		boolean complete=false;
		try{
			while(!complete){
				executor.awaitTermination(4, TimeUnit.SECONDS);
				int icount=0;
				for (Future<List<Map<String, String>>> future : list_Future) {
					if (future.isDone() || future.isCancelled())
						icount+=1;							
				}
				System.out.println("Tasks Completed: " + icount + "/" + numberOfThreads);
				if(icount>=numberOfThreads){
					complete=true;
					break;
				}
			}
		} catch (InterruptedException e) {
			
		}
	}
	
	
	public static List<String> readTextFileToList(String FilePath) throws Exception{
		File file = new File(FilePath);
		BufferedReader reader= new BufferedReader(new FileReader(file));
		List<String> lines = new ArrayList<String>(); 
		
		String line=reader.readLine();
		while (line!=null) {
			lines.add(line);
			line=reader.readLine();
		}
		
		return lines;
		
	}
	
	public static String getStringBetweenTwoChars(String input, String startChar, String endChar) {
	    try {
	        int start = input.indexOf(startChar);
	        if (start != -1) {
	            int end = input.indexOf(endChar, start + startChar.length());
	            if (end != -1) {
	                return input.substring(start + startChar.length(), end);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return input; // return null; || return "" ;
	}
	
	public static String getTagName(WebElement ele) {
		String TagName="";
		try {
			TagName=ele.getTagName();
			if (TagName==null && TagName.isEmpty())
				TagName="";
		}
		catch(Exception ex) {
			TagName="";
		}
		return TagName;
		
	}
	
	/**
	 * This method will perform mouse click using action class
	 * @param Ele
	 */
	public static void mouseClick(WebElement Ele) {
		Actions act = new Actions(driver);
		act.click(Ele).build().perform();
	}
	
	/**
	 * This method will wait for rerun text file to exist
	 * 
	 */
	public static void waitForRerunTextFile(){
		try {
				Thread.sleep(60000);
				String filePath=System.getProperty("user.dir")+File.separator+"rerun" +File.separator+ "rerun.txt";
				System.out.println("FilePath: " + filePath);
				String data = ""; 
				int count=0;
				boolean flag=false;

				while (count<5 ) {
					try {
						File f= new File(filePath);
						flag=f.exists();
						System.out.println("File Exist : "+flag );
						if (flag) {
							data = new String(Files.readAllBytes(Paths.get(filePath))); 
							System.out.println(data);
							break;
						}
					}
					catch(Exception ex) {
						System.out.println("Waiting for file..");
					}
					Thread.sleep(60000);
					count=count+1;
				}


				System.out.println("Reading text from");

			} catch (Exception e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
	}
}





