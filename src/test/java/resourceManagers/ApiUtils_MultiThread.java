package resourceManagers;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.mongodb.util.JSON;

import junit.framework.Assert;
import resourceManagers.ConfigFileReader;
import resourceManagers.Constants;
import resourceManagers.JsonValidationReporter;


public class ApiUtils_MultiThread  extends JsonValidationReporter{
	//Global Setup Variables
	public static String path;
	public static String jsonPathTerm;
	public String apiBaseURI="";
	public String apiMethod="";
	public String paraPropNameValue="";
	public static int statusCode;
	public static RestAssured restAssured;
	public static String postResponse;

	static ConfigFileReader configFileReader = new ConfigFileReader();


	//Sets base URI
	public void setApiBaseURI (String baseURI) throws IOException{
		apiBaseURI =baseURI;
		System.out.println("baseURL is>>>> "+apiBaseURI);
	}

	//Returns response
	public Response getApiResponse() {
		Response res;
		try {
			res= restAssured.get(apiBaseURI + apiMethod + paraPropNameValue);
			statusCode= res.getStatusCode();
			if (statusCode!=200) {
				assertFalse(true, "API response status code received is " + statusCode );	
			}
		}
		catch(Exception ex) {
			res=null;
		}
		return res;
	}

	public List<Map<String,String>> getJsonArrayResults(JSONObject actualJsonObj,String KeyName) {
		List<Map<String,String>> APIDataMap= new ArrayList<Map<String,String>>();
		Iterator<String> actualKeyIterator=actualJsonObj.keys();
		org.json.JSONArray actualJsonArray;

		while(actualKeyIterator.hasNext()) {	
			//Geting a key from Json Object
			String actualkey=actualKeyIterator.next().toString();
			System.out.println(actualkey);

			//Casting JSON object into Json Array
			try {
				if (actualkey.contains(KeyName)) {
					if (validateIfJasonArray(actualJsonObj, actualkey)) {
						actualJsonArray = actualJsonObj.getJSONArray(actualkey);

						System.out.println(actualJsonArray.length());
						//Traversing on the JASON Array
						for (int i=0; i<actualJsonArray.length(); i++) {
							Map<String,String> DataMap=new HashMap();
							JSONObject actualChildJsonObj=actualJsonArray.getJSONObject(i);
							Iterator<String> actualChildKeys=actualChildJsonObj.keys();
							while(actualChildKeys.hasNext()) {
								String actualKeyName=actualChildKeys.next().toString();
								Object actualkeyValue = String.valueOf(actualChildJsonObj.get(actualKeyName));

								if(actualkeyValue.equals(null)) {
									actualkeyValue="";
								}
								DataMap.put(actualKeyName,(String) actualkeyValue);

								if (actualChildJsonObj.get(actualKeyName) instanceof JSONObject)
									APIDataMap=getActualJsonResults((JSONObject)actualkeyValue,APIDataMap);
							}
							APIDataMap.add(DataMap);
						}
					}
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}
		}
		return APIDataMap;
	}

	
	public boolean validateIfJasonArray(JSONObject JsonObj,String key) {
		boolean isJsonArray=true;
		try {
			org.json.JSONArray JsonArray = JsonObj.getJSONArray(key);

		}
		catch(Exception ex) {
			isJsonArray=false;
		}
		return isJsonArray;
	}
	
	public List<Map<String,String>> getActualJsonResults(JSONObject actualJsonObj,List<Map<String,String>> apiDataMap) {
		List<Map<String,String>> APIDataMap= new ArrayList<Map<String,String>>();
		APIDataMap=apiDataMap;
		Iterator<String> actualKeyIterator=actualJsonObj.keys();
		org.json.JSONArray actualJsonArray;

		while(actualKeyIterator.hasNext()) {	
			//Geting a key from Json Object
			String actualkey=actualKeyIterator.next().toString();
			System.out.println(actualkey);

			//Casting JSON object into Json Array
			try {

				if (validateIfJasonArray(actualJsonObj, actualkey)) {
					actualJsonArray = actualJsonObj.getJSONArray(actualkey);

					System.out.println(actualJsonArray.length());
					//Traversing on the JASON Array
					for (int i=0; i<actualJsonArray.length(); i++) {
						Map<String,String> DataMap=new HashMap();
						JSONObject actualChildJsonObj=actualJsonArray.getJSONObject(i);
						Iterator<String> actualChildKeys=actualChildJsonObj.keys();
						while(actualChildKeys.hasNext()) {
							String actualKeyName=actualChildKeys.next().toString();
							Object actualkeyValue = String.valueOf(actualChildJsonObj.get(actualKeyName));

							if(actualkeyValue.equals(null)) {
								actualkeyValue="";
							}
							DataMap.put(actualKeyName,(String) actualkeyValue);

							if (actualChildJsonObj.get(actualKeyName) instanceof JSONObject)
								APIDataMap=getActualJsonResults((JSONObject)actualkeyValue,APIDataMap);
						}
						APIDataMap.add(DataMap);
					}
				}
				//If the key is not a Json Array
				else {
					Map<String,String> DataMap=new HashMap();
					Object actualkeyValue = actualJsonObj.get(actualkey);
					if(actualkeyValue.equals(null)) {
						actualkeyValue="";
					}
					String val="";
					if (actualkeyValue.getClass().getSimpleName().toLowerCase().contains("integer"))
						actualkeyValue=String.valueOf(actualkeyValue);

					DataMap.put(actualkey,(String) actualkeyValue);
					APIDataMap.add(DataMap);
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}
		}
		return APIDataMap;
	}

	//===============================================

	/*
	public String getHTTPResponse() throws JSONException, IOException {
		HttpURLConnection conn = null;
		String response = null;
		try {


			URL url = new URL(apiBaseURI + apiMethod + paraPropNameValue ); //+ MethodName + "?"
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/JSON");
			conn.setRequestProperty("Authorization", Constants.bearerGGToken);  
			conn.connect();
			System.out.println(conn.getResponseCode());
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {    
					throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
				}
			}
			else {
				System.out.println(conn.getResponseMessage());
				response=conn.getResponseMessage().toString();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return response;
	}


	public static void validateApiResponse(Response apiResponse,String expectedJsonFilePath) throws JSONException, IOException {


		JSONObject actualJson = new JSONObject(apiResponse.asString());
		JSONObject expectedJson = parseJSONFile(expectedJsonFilePath);

		int failCount=validateExpectedActualJsonResults(actualJson,expectedJson);
		assertTrue(failCount==0);

	}



	public static int validateExpectedActualJsonResults(JSONObject actualJsonObj,JSONObject expectedJsonObj ) {
		int failCount=0;

		Iterator<String> actualKeyIterator=actualJsonObj.keys();
		Iterator<String> expectedKeyIterator=expectedJsonObj.keys();
		org.json.JSONArray actualJsonArray;
		org.json.JSONArray expectedJsonArray;

		while(actualKeyIterator.hasNext()) {	
			//Geting a key from Json Object
			String actualkey=actualKeyIterator.next().toString();
			String expectedkey=expectedKeyIterator.next().toString();
			System.out.println(actualkey);

			//Casting JSON object into Json Array
			try {
				if (validateIfJasonArray(actualJsonObj, actualkey)) {
					actualJsonArray = actualJsonObj.getJSONArray(actualkey);
					expectedJsonArray = expectedJsonObj.getJSONArray(expectedkey);

					System.out.println(actualJsonArray.length());
					//Traversing on the JASON Array
					for (int i=0; i<actualJsonArray.length(); i++) {
						JSONObject actualChildJsonObj=actualJsonArray.getJSONObject(i);
						JSONObject expectedChildJsonObj=expectedJsonArray.getJSONObject(i);

						Iterator<String> actualChildKeys=actualChildJsonObj.keys();
						Iterator<String> expectedChildKeys=expectedChildJsonObj.keys();

						while(actualChildKeys.hasNext()) {
							String actualKeyName=actualChildKeys.next().toString();
							Object actualkeyValue = actualChildJsonObj.get(actualKeyName);

							String expectedKeyName=expectedChildKeys.next().toString();
							Object expectedkeyValue = expectedChildJsonObj.get(expectedKeyName);

							if (!(actualKeyName.trim().equalsIgnoreCase(expectedKeyName.trim()) && actualkeyValue.equals(expectedkeyValue)) ) {

								failCount=failCount+1;
								JsonValidationReporter.addStep("Expected:-> " + expectedKeyName + " : " + expectedkeyValue.toString() + " Actual :->" + actualKeyName + " : " + actualkeyValue.toString());
							}	
							if (actualChildJsonObj.get(actualKeyName) instanceof JSONObject)
								validateExpectedActualJsonResults((JSONObject)actualkeyValue, (JSONObject)expectedkeyValue);
						}

					}
				}
				//If the key is not a Json Array
				else {

					Object actualkeyValue = actualJsonObj.get(actualkey);
					Object expectedkeyValue = expectedJsonObj.get(expectedkey);

					if (!(actualkey.trim().equalsIgnoreCase(expectedkey.trim()) && actualkeyValue.equals(expectedkeyValue)) ) {

						failCount=failCount+1;
						JsonValidationReporter.addStep("Expected:-> " + expectedkey + " : " + expectedkeyValue.toString() + " Actual :->" + actualkey + " : " + actualkeyValue.toString());
					}
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}

		}
		return failCount;

	}




	public static int validateExpectedActualJsonResults1(JSONObject actualJsonObj,JSONObject expectedJsonObj ) {
		int failCount=0;

		Iterator<String> actualKeyIterator=actualJsonObj.keys();
		Iterator<String> expectedKeyIterator=expectedJsonObj.keys();
		org.json.JSONArray actualJsonArray;
		org.json.JSONArray expectedJsonArray;

		while(actualKeyIterator.hasNext()) {	
			//Geting a key from Json Object
			String actualkey=actualKeyIterator.next().toString();
			String expectedkey=expectedKeyIterator.next().toString();
			System.out.println(actualkey);

			//Casting JSON object into Json Array

			actualJsonArray = actualJsonObj.getJSONArray(actualkey);
			expectedJsonArray = expectedJsonObj.getJSONArray(expectedkey);

			System.out.println(actualJsonArray.length());
			//Traversing on the JASON Array
			for (int i=0; i<actualJsonArray.length(); i++) {
				JSONObject actualChildJsonObj=actualJsonArray.getJSONObject(i);
				JSONObject expectedChildJsonObj=expectedJsonArray.getJSONObject(i);

				Iterator<String> actualChildKeys=actualChildJsonObj.keys();
				Iterator<String> expectedChildKeys=expectedChildJsonObj.keys();

				while(actualChildKeys.hasNext()) {
					String actualKeyName=actualChildKeys.next().toString();
					Object actualkeyValue = actualChildJsonObj.get(actualKeyName);

					String expectedKeyName=expectedChildKeys.next().toString();
					Object expectedkeyValue = expectedChildJsonObj.get(expectedKeyName);

					if (!(actualKeyName.trim().equalsIgnoreCase(expectedKeyName.trim()) && actualkeyValue.equals(expectedkeyValue)) ) {

						failCount=failCount+1;
						JsonValidationReporter.addStep("Expected:-> " + expectedKeyName + " : " + expectedkeyValue.toString() + " Actual :->" + actualKeyName + " : " + actualkeyValue.toString());
					}	
					if (actualChildJsonObj.get(actualKeyName) instanceof JSONObject)
						validateExpectedActualJsonResults((JSONObject)actualkeyValue, (JSONObject)expectedkeyValue);
				}

			}


		}
		return failCount;

	}


	public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filename)));
		return new JSONObject(content);
	}



	public void postHTTPRequest(String FileName) throws JSONException, IOException {
		Response response;
		try {
			String FilePath="";
			if (FileName.contentEquals("AddToCartInputParameter.json"))
				FilePath=Constants.expectedTestDataFolderpath + FileName;

			URL url = new URL(apiBaseURI + apiMethod ); //+ MethodName + "?"
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", Constants.bearerToken);  
			String input = parseJSONFile(FilePath).toString();
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			conn.connect();
			System.out.println(conn.getResponseCode());
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {    
					throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
				}
			}
			else {
				System.out.println(conn.getResponseMessage());

			}
			String type = conn.getContentType();
			if (type == null) {
				return;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public Response postRequest(String FileName) throws JSONException, IOException {

		//Initializing Rest API's URL
		String APIUrl = apiBaseURI + apiMethod ;
		System.out.println(APIUrl);
		//Initializing payload or API body
		String	FilePath=Constants.inputJSONFolderpath + FileName;
		String APIBody = parseJSONFile(FilePath).toString();

		RequestSpecification requestSpec = RestAssured.with();
		requestSpec.given().contentType("application/json").body(APIBody);
		requestSpec.headers("Authorization", Constants.bearerToken);
		Response response = requestSpec.post(APIUrl);

		System.out.println("Status Code: " +response.getStatusCode());

		if (response.getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response.getStatusCode());
		}
		else {
			postResponse=response.body().asString();
			//			System.out.println(postResponse);
		}

		return response;
	}



	

	 This function will validate single key and value against simple JSON without childs/arrays
	public static void JSONFieldsValidatorForSimpleJSON(List<List<String>> fieldsToValidateList ){
		String keyToValidate=null;
		String valueToValidate=null;
		int failCount=0;
		for (int ilist=0; ilist<fieldsToValidateList.size();ilist++)
		{
			keyToValidate=fieldsToValidateList.get(ilist).get(0);
			valueToValidate=fieldsToValidateList.get(ilist).get(1);
			JSONObject actualJson = new JSONObject(postResponse);
			String actualValue=actualJson.getString(keyToValidate);
			if (!(valueToValidate.trim().equalsIgnoreCase(actualValue.trim()))) {

				failCount=failCount+1;
				JsonValidationReporter.addStep("Expected:-> " + keyToValidate + " : " + valueToValidate + " Actual :->" + actualValue);
			}
		}
		assertTrue(failCount==0);
	}

	 This function will validate a given list of key and values against actual JSON
	public static void JSONFieldsValidator(List<List<String>> fieldsToValidateList ){
		String keyToValidate=null;
		String valueToValidate=null;
		int failCount=0;
		int count=0;
		for (int ilist=0; ilist<fieldsToValidateList.size();ilist++)
		{
			keyToValidate=fieldsToValidateList.get(ilist).get(0);
			valueToValidate=fieldsToValidateList.get(ilist).get(1);
			JSONObject actualJson = new JSONObject(postResponse);
			count=validateExpectedActualFields(actualJson,keyToValidate,valueToValidate);
			failCount=failCount+count;
			System.out.println("failcount "+failCount);
		}
		assertTrue(failCount==0);
	}

	public static int validateExpectedActualFields(JSONObject actualJsonObj,String expectedKeyName, String expectedkeyValue) {
		int failCount=0;

		Iterator<String> actualKeyIterator=actualJsonObj.keys();
		org.json.JSONArray actualJsonArray;

		while(actualKeyIterator.hasNext()) {	
			//Geting a key from Json Object
			String actualkey=actualKeyIterator.next().toString();
			//System.out.println(actualkey);

			//Casting JSON object into Json Array
			try {
				if (validateIfJasonArray(actualJsonObj, actualkey)) {
					actualJsonArray = actualJsonObj.getJSONArray(actualkey);

					//System.out.println(actualJsonArray.length());
					//Traversing on the JASON Array
					for (int i=0; i<actualJsonArray.length(); i++) {
						JSONObject actualChildJsonObj=actualJsonArray.getJSONObject(i);

						Iterator<String> actualChildKeys=actualChildJsonObj.keys();

						while(actualChildKeys.hasNext()) {
							String actualKeyName=actualChildKeys.next().toString();
							Object actualkeyValue = actualChildJsonObj.get(actualKeyName);

							if(actualKeyName.trim().equalsIgnoreCase(expectedKeyName.trim())){
								if (!(actualkeyValue.equals(expectedkeyValue)) ) {
									failCount=failCount+1;
									JsonValidationReporter.addStep("Expected:-> " + expectedKeyName + " : " + expectedkeyValue.toString() + " Actual :->" + actualKeyName + " : " + actualkeyValue.toString());
								}	
							}
							if (actualChildJsonObj.get(actualKeyName) instanceof JSONObject)
								validateExpectedActualFields((JSONObject)actualkeyValue, expectedKeyName,expectedkeyValue);
						}

					}
				}
				//If the key is not a Json Array
				else {

					Object actualkeyValue = actualJsonObj.get(actualkey);

					if(actualkey.trim().equalsIgnoreCase(expectedKeyName.trim())){
						if (!(actualkeyValue.equals(expectedkeyValue)) ) {
							failCount=failCount+1;
							JsonValidationReporter.addStep("Expected:-> " + expectedKeyName + " : " + expectedkeyValue.toString() + " Actual :->" + actualkey + " : " + actualkeyValue.toString());
						}
					}
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}

		}
		return failCount;

	}

	 This function is to compare expected and actual JSONs. It will validate complete details like every key and value combinations with case sensitive

	public void validateCompleteJsonFile(String expectedJsonFilePath) throws IOException {

		System.out.println(getApiResponse().asString());
		JSONObject actualJson = new JSONObject(getApiResponse().asString()) ;
		JSONObject expectedJson = parseJSONFile(expectedJsonFilePath);
		//JSONObject actualJson =parseJSONFile(actualJsonFilePath);

		ObjectMapper mapper = new ObjectMapper();
		assertEquals(mapper.readTree(expectedJson.toString()),mapper.readTree(actualJson.toString()));


	}

	 This function is to compare expected and actual JSONs. It will validate complete details like every key and value combinations with case sensitive

	public static void validateCompleteJsonFileForPostResponse(String expectedJsonFilePath) throws IOException {

		System.out.println(postResponse);

		JSONObject actualJson = new JSONObject(postResponse) ;
		JSONObject expectedJson = parseJSONFile(expectedJsonFilePath);


		ObjectMapper mapper = new ObjectMapper();

		System.out.println(mapper.readTree(expectedJson.toString()).toString());
		System.out.println(mapper.readTree(actualJson.toString()).toString());

		assertEquals(mapper.readTree(expectedJson.toString()),mapper.readTree(actualJson.toString()));
	}

	public void validateXMLResponse(String expectedXMLFilePath) {
		String actualData=getApiResponse().asString();
		String ExpectedXMLData=readXMLFile(expectedXMLFilePath);
		assertXMLEquals(ExpectedXMLData, actualData);
	}

	public static void removeSpecialCharactersFromResponse() {
		if (postResponse.contains("¥"))
			postResponse=postResponse.replaceAll("¥", "");
		if (postResponse.contains("?"))
			postResponse=postResponse.replaceAll("?", ".");
		String findString=	postResponse.substring(postResponse.indexOf("\"formattedPrice\""), postResponse.length());
		String findString1=findString.substring(0, findString.indexOf("\",")+2);
		postResponse=postResponse.replace(findString1, "");
	}

	public static void assertXMLEquals(String expectedXML, String actualXML)  {
		try {
			XMLUnit.setIgnoreWhitespace(true);
			XMLUnit.setIgnoreAttributeOrder(true);

			DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));

			List<?> allDifferences = diff.getAllDifferences();
			Assert.assertEquals("Differences found: "+ diff.toString(), 0, allDifferences.size());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}



	public static void createXMLFile(String data, String FileName) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(Constants.expectedJSONFolderpath + FileName));
			os.write(data.getBytes(), 0, data.length());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String readXMLFile(String filePath) {
		// our XML file for this example
		String xml2String=null;
		try {
			File xmlFile = new File(filePath);


			Reader fileReader = new FileReader(xmlFile);
			BufferedReader bufReader = new BufferedReader(fileReader);

			StringBuilder sb = new StringBuilder();
			String line = bufReader.readLine();
			while( line != null){
				sb.append(line).append("\n");
				line = bufReader.readLine();
			}
			xml2String = sb.toString();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

		return xml2String;
	}



	


	 */


}
