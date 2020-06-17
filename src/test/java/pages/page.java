package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import resourceManagers.CommonUtilities;
import resourceManagers.DriverConfig;

public class CustomAntisenseLNAGapmerConfigurePage extends BasePage {
	public String plateNameEnteredBulkPlate;
	CustomAntisenseLNAGapmeRPage customAntisenseLNAGapmeRPage = PageFactory.initElements(driver, CustomAntisenseLNAGapmeRPage.class);
	QIAseqTargetedDNACustomPanelsPage  qIAseqTargetedDNACustomPanelsPage = PageFactory.initElements(driver, QIAseqTargetedDNACustomPanelsPage.class);

	public CustomAntisenseLNAGapmerConfigurePage() {
		super(DriverConfig.driver);
	}



	@FindBy(how = How.XPATH, using ="//*[@class='breadcrumb']")
	WebElement breadcrumb;
	@FindBy(how = How.XPATH, using ="//*[@class='container-fluid']/h3/span[1]")
	WebElement designerName;
	@FindBy(how = How.XPATH, using ="//*[@class='container-fluid']/h3/span[2]")
	WebElement designerStatus;
	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[1]/div/div[1]/div[2]")
	WebElement designRequestID;
	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[1]/div/div[2]/div[2]")
	WebElement designName;
	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[1]/div/div[3]/div[2]")
	WebElement designRegion;
	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[1]/div/div[4]/div[2]")
	WebElement organism;
	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[1]/div/div[5]/div[2]")
	WebElement isPrimaryTranscript;
	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[1]/div/div[6]/div[2]")
	WebElement isNovelRNA;
	@FindBy(how = How.XPATH, using ="//canvas")
	WebElement coverageRegionBar;

	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[3]/div//span")
	WebElement designTableDesignName;

	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[3]/div/div[2]")
	WebElement designTableExcellentDesignScore;

	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[3]/div/div[3]")
	WebElement designTableGoodDesignScore;

	@FindBys({ @FindBy(how = How.XPATH, using = "//ul[@class='list-unstyled']//label")})
	List<WebElement> gapmeRsNameList;

	@FindBys({ @FindBy(how = How.XPATH, using = "//ul[@class='list-unstyled']//div[@class='custom-control custom-checkbox']")})
	List<WebElement> gapmeRsCheckboxList;

	@FindBy(how = How.XPATH, using ="//select[@formcontrolname='purification']")
	WebElement selectPurification;
	@FindBy(how = How.XPATH, using ="//select[@formcontrolname='sample']")
	WebElement selectSampleQuality;
	@FindBy(how = How.XPATH, using ="//select[@formcontrolname='modification']")
	WebElement selectModification;

	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[6]/div[2]//h4")
	WebElement gapmeRSNameSelected;

	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[6]/div[2]//h4/../div/span")
	WebElement gapmeRSNameDescriptionSelected;

	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[6]/div[2]//h4/..//div/a")
	WebElement resourceLink;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[6]/div[2]//h5")})
	List<WebElement> productNoCatalogNoPricing;

	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']//app-configure/div[5]/div/div[2]//p")
	WebElement helpText;
	//*[@id='SelectConfigure']//app-configure/div[5]/div/div[2]/h3")

	@FindBy(how = How.XPATH, using ="//*[@id='SelectConfigure']/div/app-configure/div[6]/div//button")
	WebElement saveAndNextButton;

	@FindBy(how = How.XPATH, using ="//*[@id='AddControls']//app-select-controls/div[1]/div/div[1]/p")
	WebElement gapmersSelectedCountDesignCompleted;

	@FindBy(how = How.XPATH, using ="//*[@id='AddControls']//app-select-controls/div[1]/div/div[1]/p/../following-sibling::div//a")
	WebElement showSelectedGapmersAccordianDesignCompleted;

	@FindBy(how = How.XPATH, using ="//*[@id='collapseSelected']/div/div/div[1]/div/h5")
	WebElement gapmersNameDesignCompleted;

	@FindBy(how = How.XPATH, using ="//*[@id='collapseSelected']/div//a")
	WebElement resourceLinkDesignCompleted;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapseSelected']/div/div/div[2]/div[2]//p/span")})
	List<WebElement> productNoCatalogNoPricingDesignCompleted;

	@FindBy(how = How.XPATH, using ="//*[@id='AddControls-tabq']")
	WebElement SelectControlsTab;

	@FindBy(how = How.XPATH, using ="//*[@id='AddControls']//app-select-controls//h5[contains(text(),'Selected')]")
	WebElement SelectedGapmeRs;

	@FindBy(how = How.XPATH, using ="//*[@id='AddControls']//app-select-controls//h5[contains(text(),'Selected')]/../../div/a")
	WebElement ShowSelectedGapmerLink;

	@FindBy(how = How.XPATH, using ="//*[@id='AddControls']//app-select-controls/div[3]/div/div")
	WebElement helpNoteDesignCompleted;

	@FindBy(how = How.XPATH, using ="//*[@id='AddControls']//app-select-controls/div//h5[contains(text(),'Negative')]")
	WebElement negativeControlDesignCompleted;

	@FindBy(how = How.XPATH, using ="//app-select-controls//h5[contains(text(),'Negative Controls')]/../../div/a")
	WebElement showNegativeControlDesignCompleted;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapseNegative']//div/div/div[2]/h4")})
	List<WebElement> negativeControlNameList;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapseNegative']//div/div/div[1]/input/..")})
	List<WebElement> negativeControlNameCheckboxList;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapseNegative']//div/div/div[2]//p/span[contains(text(),'Positive and Negative')]")})
	List<WebElement> negativeControlNameDescriptionList;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapseNegative']//div/div/div[1]/input/../../../div[3]")})
	List<WebElement> negativeControlSelectGroupList; 

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapseNegative']//div/div/div[1]/input/../../../div[5]//h5/span") }) 
	List<WebElement> negativeControlProductCatalogPriceList; //  //p/span

	@FindBy(how = How.XPATH, using ="//*[@id='AddControls']//app-select-controls/div//h5[contains(text(),'Positive')]")
	WebElement positiveControlDesignCompleted;

	@FindBy(how = How.XPATH, using ="//app-select-controls//h5[contains(text(),'Positive')]/../../div/a")
	WebElement showPositiveControlDesignCompleted;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapsePositive']//div/div/div[2]/h4")})
	List<WebElement> positiveControlNameList;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapsePositive']//div/div/div[1]/input/..")})
	List<WebElement> positiveControlNameCheckboxList;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapsePositive']//div/div/div[2]//p/span[contains(text(),'Positive and Negative')]")})
	List<WebElement> positiveControlNameDescriptionList;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapsePostive']//div//h5/../../div[3]/div/div[2]/div[1]")})
	List<WebElement> positiveControlSelectGroupList; //  //Select

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapsePositive']//div/div/div[1]/input/../../../div[3]")})
	List<WebElement> positiveControlSelectGroupList1;

	@FindBys({@FindBy(how = How.XPATH, using ="//*[@id='collapsePositive']//div/div/div[1]/input/../../../div[5]//h5/span") }) 
	List<WebElement> positiveControlProductCatalogPriceList; //  //p/span

	@FindBy(how = How.XPATH, using ="//h5[contains(text(),'Total')]/../h5/strong")
	WebElement totalPrice;

	@FindBy(how = How.XPATH, using ="//a[contains(text(),' order online')]")
	WebElement CantOrderOnline;

	@FindBy(how = How.XPATH, using ="//p[contains(text(),'Would you like to change your design')]")
	WebElement 	WouldLikeToChangeYourDesign;	

	@FindBy(how = How.XPATH, using ="//button[contains(text(),'Redesign')]")
	WebElement reDesignBtn;

	@FindBy(how = How.XPATH, using ="//button[contains(text(),'Reconfigure')]")
	WebElement reConfigureBtn;


	@FindBy(how = How.XPATH, using ="//button[contains(text(),'ADD TO CART')]")
	WebElement addToCartBtn;


	@FindBy(how = How.XPATH, using ="//h5[@id='thankyou']")
	WebElement thankYouHeading;

	@FindBy(how = How.XPATH, using ="//div[@id='thankyou']//P")
	WebElement thankYouDesc;

	@FindBy(how = How.XPATH, using ="//a[contains(text(),'View My Designs')]")
	WebElement viewMyDesignBtn;

	@FindBy(how = How.XPATH, using ="//a[contains(text(),'Design New')]")
	WebElement designNewBtn;

	@FindBy(how = How.XPATH, using ="//a[contains(text(),'Show Cart')]")
	WebElement showCartBtn;

	@FindBy(how = How.XPATH, using ="//a[contains(@class,'nav-link active')]/span[contains(text(),'Configure')]")
	WebElement configurePageActive;

	@FindBy(how = How.XPATH, using ="//gg-lib-share-designers/button")
	WebElement btnShare;

	@FindBy(how = How.XPATH, using ="//input[@id='shareDesignerEmail']")
	WebElement txtShareEmailID;

	@FindBy(how = How.XPATH, using ="//div[@class='modal-footer']/button[contains(text(),'Share')]")
	WebElement btnShareEmail;

	@FindBy(how = How.XPATH, using ="//div[@id='shareDesignSuccessModal']//button")
	WebElement btnDesignShareSuccessPopUpCross;

	//==============================================================================================
	//										Methods
	//=============================================================================================
	
	/**
	 * This method will fill and validate Configure page
	 * @param Scenarios
	 * @return void
	 */
	public void fillAndValidateDesignConfigurePage(List<String> Scenarios) {
		if (verifyReconfigureBtn()) {
			clickReconfigure();
		}
		CommonUtilities.configureWait("normalwait", 10);
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, designerName, 20);
		for (int iList=0; iList<Scenarios.size(); iList++) {
			switch(Scenarios.get(iList).trim()) {

			case "Breadcrumb":
				assertTrue(breadcrumb.isDisplayed(),"Breadcrumb is not displayed on Design Configure Page.");
				break;

			case "Designer Name as provided by the user and status as Design Validated":
				assertTrue(designerName.isDisplayed(),"Designer Name provided by the user is not displayed on Design Configure Page.");
				break;

			case "Design Request ID":
				assertTrue(designRequestID.isDisplayed(),"Design Request ID is not displayed on Design Configure Page.");
				break;

			case "Design Name":
				assertTrue(designName.isDisplayed(),"Design Name is not displayed on Design Configure Page.");
				break;

			case "Design Region":
				assertTrue(designRegion.isDisplayed(),"Design Region is not displayed on Design Configure Page.");
				break;

			case "Organism":
				assertTrue(organism.isDisplayed(),"Organism is not displayed on Design Configure Page.");
				break;

			case "Is Primary Transcript":
				assertTrue(isPrimaryTranscript.isDisplayed(),"Is Primary Transcript is not displayed on Design Configure Page.");
				break;

			case "Is Novel RNA":
				assertTrue(isNovelRNA.isDisplayed(),"Is Novel RNA is not displayed on Design Configure Page.");
				break;

			case "A bar with coverage region is displayed":
				assertTrue(coverageRegionBar.isDisplayed(),"A bar with coverage region is not displayed on Design Configure Page.");
				break;

			case "A table -and Design Name , Design score indexes (Excellent in green  and Good  in yellow":
				assertTrue(designTableDesignName.isDisplayed(),"A table with Design Name is not displayed on Design Configure Page.");
				assertTrue(designTableExcellentDesignScore.isDisplayed(),"A table with Design score indexes (Excellent) is not displayed on Design Configure Page.");
				assertTrue(designTableGoodDesignScore.isDisplayed(),"A table with Design score indexes (Good) is not displayed on Design Configure Page.");
				break;

			case "The name of the GapmeRs must be in the format 'Design Name Provided by user_Antisense LNA GapmeR'":
				assertTrue(gapmeRsNameList.get(0).getText().contains("Antisense LNA GapmeR"),"The name of the GapmeRs in the format 'Design Name Provided by user_Antisense LNA GapmeR'  is not displayed on Design Configure Page.");
				driver.get(driver.getCurrentUrl());
				CommonUtilities.configureWait("normalwait", 10);
				CommonUtilities.mouseClick(gapmeRsCheckboxList.get(0));
//				gapmeRsCheckboxList.get(0).click();
				break;

			case "Below this Configuration indexes- Drop down fields must be displayed under the text - Select":
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, selectPurification, 10);
				selectPurification.click();
				CommonUtilities.selectbyIndex(selectPurification, 1);
				CommonUtilities.selectbyIndex(selectSampleQuality, 1);
				CommonUtilities.selectbyIndex(selectModification, 1);
				break;

			case "The GapmeRs selected by the user must be displayed at the bottom with the name, description ,resources link , product number, catalog number and price":
				assertTrue(gapmeRSNameSelected.isDisplayed(),"Selected GapmeRs Name is not displayed on Design Configure Page.");
				assertTrue(resourceLink.isDisplayed(),"Resources link is not displayed on Design Configure Page.");
				assertTrue(productNoCatalogNoPricing.get(0).isDisplayed(),"Product No. is not displayed on Design Configure Page.");
				assertTrue(productNoCatalogNoPricing.get(1).isDisplayed(),"Catalog No is not displayed on Design Configure Page.");
				assertTrue(productNoCatalogNoPricing.get(2).isDisplayed(),"Price is not displayed on Design Configure Page.");	
				break;


			case "Save and Next button must be displayed at the bottom of the page to Navigate to the Next step - Select Controls":
				assertTrue(saveAndNextButton.isDisplayed(),"Save And NextButton is not displayed on Design Configure Page.");
				break;
			}
		}
	}

	
	/**
	 * This method will click the design Name checkbox
	 * @param DesinIndex
	 * @return void
	 */
	public void clickOnDesignNameDesignTable(int DesinIndex) {
		CommonUtilities.configureWait("normalwait", 7);
		gapmeRsCheckboxList.get(DesinIndex).click();
	}

	/**
	 * This method will validate the dropdowns
	 * @param NA
	 * @return void
	 */
	public void validateDropDowns() {
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, selectPurification, 10);
		selectPurification.click();
		CommonUtilities.selectbyIndex(selectPurification, 1);
		assertTrue(helpText.isDisplayed(),"Help Text is not displayed on Design Configure Page.");
		CommonUtilities.selectbyIndex(selectSampleQuality, 1);
		assertTrue(helpText.isDisplayed(),"Help Text is not displayed on Design Configure Page.");
		CommonUtilities.selectbyIndex(selectModification, 1);
		assertTrue(helpText.isDisplayed(),"Help Text is not displayed on Design Configure Page.");
	}

	/**
	 * This method will validate the Price
	 * @param NA
	 * @return void
	 */
	public void validatePricing() {
		assertTrue(productNoCatalogNoPricing.get(2).isDisplayed(),"Price is not displayed on Design Configure Page.");	
	}

	/**
	 * This method will configure the design
	 * @param DesinIndex
	 * @param purificationIndex
	 * @param sampleIndex
	 * @param ModificationIndex
	 * @return void
	 */
	public void configureDesign(int DesinIndex, int purificationIndex, int sampleIndex,int ModificationIndex) {
		clickOnDesignNameDesignTable(DesinIndex);
		CommonUtilities.dynamicWait(selectPurification, 10);
		selectPurification.click();
		CommonUtilities.selectbyIndex(selectPurification, purificationIndex);
		CommonUtilities.selectbyIndex(selectSampleQuality, sampleIndex);
		CommonUtilities.selectbyIndex(selectModification, ModificationIndex);
	}
	
	public void selectModification(int ModificationIndex) {
		CommonUtilities.selectbyIndex(selectModification, ModificationIndex);
	}

	/**
	 * This method will click on Save and Next button
	 * @param NA
	 * @return void
	 */
	public void clickSaveAndNextBtn() {
		CommonUtilities.dynamicWait(saveAndNextButton, 10);
		CommonUtilities.mouseClick(saveAndNextButton);
	}
	
	public boolean isUserOnConfigPage() {
		boolean flag=false;
		try {
		if (saveAndNextButton.getTagName().length()>0) {
			flag=true;
		}
		}
		catch(Exception ex) {
			
		}
		return flag;
				
	}

	/**
	 * This method will validate the control page
	 * @param NA
	 * @return void
	 */
	public void verifyControlStepHasSelectedGapmeRSection()  {
		try{
			CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, showSelectedGapmersAccordianDesignCompleted, 30);
			String GapmerName=gapmersSelectedCountDesignCompleted.getText();
			System.out.println(GapmerName);
			String count=GapmerName.substring(GapmerName.indexOf("(")+1, GapmerName.indexOf(")"));
			System.out.println(count);
			CommonUtilities.configureWait("normalwait", 2);

			//validate Count of GapmeRS selected by the user
			assertTrue(StringUtils.isNumeric(count),"Count of GapmeRS selected by the user is not displayed on Design Control Page.");
			if (!CommonUtilities.isVisible(resourceLinkDesignCompleted, 5)){
				showSelectedGapmersAccordianDesignCompleted.click();
				CommonUtilities.configureWait("normalwait", 2);
			}
			//validate GapmeR name, description, resources, product number, catalog number and price
			assertTrue(gapmersNameDesignCompleted.isDisplayed(),"Selected GapmeRs Name is not displayed on Design Control Page.");
			assertTrue(resourceLinkDesignCompleted.isDisplayed(),"Resources link is not displayed on Design Control Page.");
			assertTrue(productNoCatalogNoPricingDesignCompleted.get(0).isDisplayed(),"Product No. is not displayed on Design Control Page.");
			assertTrue(productNoCatalogNoPricingDesignCompleted.get(1).isDisplayed(),"Catalog No is not displayed on Design Control Page.");
			assertTrue(productNoCatalogNoPricingDesignCompleted.get(2).isDisplayed(),"Price is not displayed on Design Control Page.");	

			//validate Help Note
			assertTrue(helpNoteDesignCompleted.isDisplayed(),"Help Note is not displayed on Design Control Page.");
		}
		catch(Exception ex) {

		}
	}


	/**
	 * This method will validate the Positive and Negative controls on control page
	 * @param NA
	 * @return void
	 */
	public void verifyNegativePositiveControlStep(List<String> Fields) {
		verifyNegativeControlStep(Fields);
		verifyPositiveControlStep(Fields);
	}

	/**
	 * This method will validate the Negative controls on control page
	 * @param NA
	 * @return void
	 */
	public void verifyNegativeControlStep(List<String> Fields) {
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, negativeControlDesignCompleted, 10);
		for (int iList=0; iList<Fields.size(); iList++) {
			switch(Fields.get(iList).trim()) {

			case "Count of Negative controls":
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, negativeControlDesignCompleted, 30);
				CommonUtilities.configureWait("normalwait", 5);
				String GapmerName=negativeControlDesignCompleted.getText();
				String count=GapmerName.substring(GapmerName.indexOf("(")+1, GapmerName.indexOf(")"));
				System.out.println(count); 

				//validate Count of GapmeRS selected by the user
				assertTrue(StringUtils.isNumeric(count),"Count of Negative Control selected by the user is not displayed on Design Control Page.");
				break;

			case "Control Name":
				CommonUtilities.configureWait("normalwait", 2);
				showNegativeControlDesignCompleted.click();
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, negativeControlNameList.get(0), 30);
				assertTrue(negativeControlNameList.get(0).isDisplayed(),"Negative Control Name is not displayed on Design Control Page.");
				break;

			case "Description":
				assertTrue(negativeControlNameDescriptionList.get(0).isDisplayed(),"Negative Control Name Description is not displayed on Design Control Page.");
				break;

			case "Configuraion options(same as GapmeR) which is enabled after user clicks on the check box with it":
				negativeControlNameCheckboxList.get(0).click();
				List<WebElement> SelectElement=CommonUtilities.findChildElements(negativeControlSelectGroupList.get(0), "//select");
				assertTrue(SelectElement.get(0).isEnabled(),"Purification Dropdown is not Enabled on Design Control Page.");
				assertTrue(SelectElement.get(1).isEnabled(),"Quantity Dropdown is not Enabled on Design Control Page.");
				assertTrue(SelectElement.get(2).isEnabled(),"Modification Dropdown is not Enabled on Design Control Page.");
				break;

			case "product number , catalog number and price":
				assertTrue(negativeControlProductCatalogPriceList.get(0).isDisplayed(),"Product Number is not Displayed on Design Control Page.");
				assertTrue(negativeControlProductCatalogPriceList.get(1).isDisplayed(),"Catalog Number is not Displayed on Design Control Page.");
				assertTrue(negativeControlProductCatalogPriceList.get(2).isDisplayed(),"Price is not Displayed on Design Control Page.");
				break;
			}
		}
		showNegativeControlDesignCompleted.click();
	}

	/**
	 * This method will validate the Positive controls on control page
	 * @param NA
	 * @return void
	 */
	public void verifyPositiveControlStep(List<String> Fields) {
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, positiveControlDesignCompleted, 10);
		for (int iList=0; iList<Fields.size(); iList++) {
			switch(Fields.get(iList).trim()) {

			case "Count of Positive controls":
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, showPositiveControlDesignCompleted, 10);
				String GapmerName=positiveControlDesignCompleted.getText();
				String count=GapmerName.substring(GapmerName.indexOf("(")+1, GapmerName.indexOf(")")).trim();
				System.out.println(count);

				//validate Count of GapmeRS selected by the user
				assertTrue(StringUtils.isNumeric(count),"Count of Positive Control selected by the user is not displayed on Design Control Page.");
				break;

			case "Control Name":
				CommonUtilities.configureWait("normalwait", 2);
				showPositiveControlDesignCompleted.click();
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, positiveControlNameList.get(0), 10);
				System.out.println(positiveControlNameList.get(0));
				assertTrue(positiveControlNameList.get(0).isDisplayed(),"Positive Control Name is not displayed on Design Control Page.");
				break;

			case "Description":
				assertTrue(positiveControlNameDescriptionList.get(0).isDisplayed(),"Positive Control Name Description is not displayed on Design Control Page.");
				break;

			case "Configuraion options(same as GapmeR) which is enabled after user clicks on the check box with it":
				positiveControlNameCheckboxList.get(0).click();
				List<WebElement> SelectElement=CommonUtilities.findChildElements(positiveControlSelectGroupList1.get(0), "//select");
				assertTrue(SelectElement.get(0).isEnabled(),"Purification Dropdown is not Enabled on Design Control Page.");
				assertTrue(SelectElement.get(1).isEnabled(),"Quantity Dropdown is not Enabled on Design Control Page.");
				assertTrue(SelectElement.get(2).isEnabled(),"Modification Dropdown is not Enabled on Design Control Page.");
				break;

			case "product number , catalog number and price":
				assertTrue(positiveControlProductCatalogPriceList.get(0).isDisplayed(),"Product Number is not Displayed on Design Control Page.");
				assertTrue(positiveControlProductCatalogPriceList.get(1).isDisplayed(),"Catalog Number is not Displayed on Design Control Page.");
				assertTrue(positiveControlProductCatalogPriceList.get(2).isDisplayed(),"Price is not Displayed on Design Control Page.");
				break;
			}
		}
	}
	
	/**
	 * This method will select Negative control on control page
	 * @param NA
	 * @return void
	 */
	public void clickOneNegativeControl() {
		CommonUtilities.configureWait("normalwait", 2);
		//click on Show link for Negative controls
		showNegativeControlDesignCompleted.click();
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, negativeControlNameList.get(0), 10);
		negativeControlNameCheckboxList.get(0).click();

	}


	/**
	 * This method will validate price is updating when the drop down values are changed Negative controls on control page
	 * @param NA
	 * @return void
	 */
	public void validatePriceUpdatingWithChangeInDropdownOptionsNegativeControls() {
		String InitialTotalPrice="";
		String ChangeInTotalPrice="";
		CommonUtilities.configureWait("normalwait", 4);
		List<WebElement> SelectElement=CommonUtilities.findChildElements(negativeControlSelectGroupList.get(0), "//select");
		//Select First Index of all the dropdowns
		CommonUtilities.selectbyIndex(SelectElement.get(0), 0);
		CommonUtilities.selectbyIndex(SelectElement.get(1), 0);
		CommonUtilities.selectbyIndex(SelectElement.get(2), 0);
		CommonUtilities.configureWait("normalwait", 4);

		//Get initial Price
		String InitialPrice=negativeControlProductCatalogPriceList.get(2).getText();
		InitialTotalPrice=totalPrice.getText();

		if (!InitialTotalPrice.contains("Inquire")) {
			//Change The Dropdown value for Purification
			CommonUtilities.selectbyIndex(SelectElement.get(0), 1);
			CommonUtilities.configureWait("normalwait", 4);
			String ChangePurificationPrice=negativeControlProductCatalogPriceList.get(2).getText();
			ChangeInTotalPrice=totalPrice.getText();
			System.out.println(InitialPrice);
			System.out.println(ChangePurificationPrice);
			if (!ChangePurificationPrice.contains("inquire")) {
				assertTrue(!InitialPrice.equalsIgnoreCase(ChangePurificationPrice),"Negative Control Price is not Updated when Dropdown value for Purification is changed on Design Control Page.");
				assertTrue(!InitialTotalPrice.equalsIgnoreCase(ChangeInTotalPrice),"Total Price is not Updated when Dropdown value for Purification is changed under Negative Control section  on Design Control Page.");
			}
			//Change the values of Inital Prices
			InitialPrice=ChangePurificationPrice;
			InitialTotalPrice=ChangeInTotalPrice;

			//Change The Dropdown value for Quantity
			CommonUtilities.selectbyIndex(SelectElement.get(1), 1);
			CommonUtilities.configureWait("normalwait", 4);
			String ChangeQuantityPrice=negativeControlProductCatalogPriceList.get(2).getText();
			ChangeInTotalPrice=totalPrice.getText();
			if (!ChangeQuantityPrice.contains("inquire")) {
				assertTrue(!InitialPrice.equalsIgnoreCase(ChangeQuantityPrice),"Negative Control Price is not Updated when Dropdown value for Quantity is changed on Design Control Page.");
				assertTrue(!InitialTotalPrice.equalsIgnoreCase(ChangeInTotalPrice),"Total Price is not Updated when Dropdown value for Quantity is changed under Negative Control section  on Design Control Page.");
			}
			//Change the values of Inital Prices
			InitialPrice=ChangeQuantityPrice;
			InitialTotalPrice=ChangeInTotalPrice;


			//Change The Dropdown value for Modification
			CommonUtilities.selectbyIndex(SelectElement.get(2), 1);
			CommonUtilities.configureWait("normalwait", 4);
			String ChangeModificationPrice=negativeControlProductCatalogPriceList.get(2).getText();
			ChangeInTotalPrice=totalPrice.getText();
			if (!ChangeModificationPrice.contains("inquire")) {
				assertTrue(!InitialPrice.equalsIgnoreCase(ChangeModificationPrice),"Negative Control Price is not Updated when Dropdown value for Modification is changed on Design Negative Control Page.");
				assertTrue(!InitialTotalPrice.equalsIgnoreCase(ChangeInTotalPrice),"Total Price is not Updated when Dropdown value for Modification is changed under Negative Control section  on Design Control Page.");
			}
		}
		showNegativeControlDesignCompleted.click();
	}

	/**
	 * This method will validate price is updating when the drop down values are changed Positive controls on control page
	 * @param NA
	 * @return void
	 */
	public void validatePriceUpdatingWithChangeInDropdownOptionsPositiveControls() {
		String InitialTotalPrice="";
		String ChangeInTotalPrice="";

		List<WebElement> SelectElement=CommonUtilities.findChildElements(positiveControlSelectGroupList1.get(0), "//select");
		
		//Select First Index of all the dropdowns
		CommonUtilities.selectbyIndex(SelectElement.get(0), 0);
		CommonUtilities.selectbyIndex(SelectElement.get(1), 0);
		CommonUtilities.selectbyIndex(SelectElement.get(2), 1);
		CommonUtilities.configureWait("normalwait", 4);

		//Get initial Price
		String InitialPrice=positiveControlProductCatalogPriceList.get(2).getText();
		InitialTotalPrice=totalPrice.getText();
		if (!InitialTotalPrice.contains("Inquire")){
			//Change The Dropdown value for Purification
			CommonUtilities.selectbyIndex(SelectElement.get(0), 1);
			CommonUtilities.configureWait("normalwait", 4);
			String ChangePurificationPrice=positiveControlProductCatalogPriceList.get(2).getText();
			ChangeInTotalPrice=totalPrice.getText();
			if (!ChangePurificationPrice.contains("inquire")) {
				assertTrue(!InitialPrice.equalsIgnoreCase(ChangePurificationPrice),"Positive Control Price is not Updated when Dropdown value for Purification is changed on Design Control Page.");
				assertTrue(!InitialTotalPrice.equalsIgnoreCase(ChangeInTotalPrice),"Total Price is not Updated when Dropdown value for Purification under Positive Control section  is changed on Design Control Page.");
			}
			//Change the values of Inital Prices
			InitialPrice=ChangePurificationPrice;
			InitialTotalPrice=ChangeInTotalPrice;

			//Change The Dropdown value for Quantity
			CommonUtilities.selectbyIndex(SelectElement.get(1), 1);
			CommonUtilities.configureWait("normalwait", 4);
			String ChangeQuantityPrice=positiveControlProductCatalogPriceList.get(2).getText();
			ChangeInTotalPrice=totalPrice.getText();
			if (!ChangeQuantityPrice.contains("inquire")) {
				assertTrue(!InitialPrice.equalsIgnoreCase(ChangeQuantityPrice),"Positive Control Price is not Updated when Dropdown value for Quantity is changed on Design Control Page.");
				assertTrue(!InitialTotalPrice.equalsIgnoreCase(ChangeInTotalPrice),"Total Price is not Updated when Dropdown value for Quantity under Positive Control section is changed on Design Control Page.");
			}
			//Change the values of Inital Prices
			InitialPrice=ChangeQuantityPrice;
			InitialTotalPrice=ChangeInTotalPrice;
		}

	}

	/**
	 * This method will select the Positive control on control page
	 * @param NA
	 * @return void
	 */
	public void clickOnePositiveControl() {
		CommonUtilities.configureWait("normalwait", 2);
		//click on Show link for Negative controls
		showPositiveControlDesignCompleted.click();
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Clickable, positiveControlNameCheckboxList.get(0), 10);
		positiveControlNameCheckboxList.get(0).click();

	}

	/**
	 * This method will click on re-design button on control page
	 * @param NA
	 * @return void
	 */
	public void clickRedesign() {
		CommonUtilities.dynamicWait(reDesignBtn, 10);
		CommonUtilities.forceClick(reDesignBtn);
	}
	
	/**
	 * This method will validate that design page is pre-filled
	 * @param NA
	 * @return void
	 */
	public void validateFirstDesignPageIsPreFilled() {
		customAntisenseLNAGapmeRPage.validateDesignFirstPageIsPreFilled();
	}

	/**
	 * This method will click on re-configure button on control page
	 * @param NA
	 * @return void
	 */
	public void clickReconfigure() {
		CommonUtilities.configureWait("normalwait", 8);
		CommonUtilities.forceClick(reConfigureBtn);
		CommonUtilities.configureWait("normalwait", 3);

	}

	/**
	 * This method will verify re-configure button on control page
	 * @param NA
	 * @return void
	 */
	public boolean verifyReconfigureBtn() {
		boolean flag=false;
		try {
			if (reConfigureBtn.isDisplayed()) {
				flag=true;
			}
		}
		catch(Exception ex) {

		}
		return flag;
	}

	/**
	 * This method will verify taht user is naviagated to Configure page
	 * @param NA
	 * @return void
	 */
	public void validateUserIsNavigatedToConfigurePage() {
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, configurePageActive, 30);
		assertTrue(configurePageActive.isDisplayed(),"User Is not Navigated To ConfigurePage");

	}

	/**
	 * This method will click on Add to Cart button on control page
	 * @param NA
	 * @return void
	 */
	public void clickAddToCart() {
		CommonUtilities.forceClick(addToCartBtn);
		try {
			CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, viewMyDesignBtn, 60);
		}
		catch(Exception e) {
			CommonUtilities.forceClick(addToCartBtn);
		}
	}

	/**
	 * This method will verify Add to Cart Pop Up
	 * @param NA
	 * @return void
	 */
	public void verifyAddToCartPopUp(List<String> Fields) {
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, viewMyDesignBtn, 60);
		for (int iList=0; iList<Fields.size(); iList++) {
			switch(Fields.get(iList).trim()) {

			case "Thank You":
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, thankYouHeading, 10);
				//validate Count of GapmeRS selected by the user
				assertTrue(thankYouHeading.isDisplayed(),"Thank You Heading is not displayed on Add To Cart popup.");
				break;

			case "Text - All your desired Products have been added to cart":
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, thankYouDesc, 10);
				//validate Count of GapmeRS selected by the user
				assertTrue(thankYouDesc.isDisplayed(),"Thank You Heading is not displayed on Add To Cart popup.");
				break;

			case "View My Designs":
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, viewMyDesignBtn, 10);
				//validate Count of GapmeRS selected by the user
				assertTrue(viewMyDesignBtn.isEnabled(),"Thank You Heading is not displayed on Add To Cart popup.");
				break;

			case "Design New":
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, designNewBtn, 10);
				//validate Count of GapmeRS selected by the user
				assertTrue(designNewBtn.isEnabled(),"Thank You Heading is not displayed on Add To Cart popup.");
				break;

			case "Show Cart":
				CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, showCartBtn, 10);
				//validate Count of GapmeRS selected by the user
				assertTrue(showCartBtn.isEnabled(),"Thank You Heading is not displayed on Add To Cart popup.");
				break;
			}
		}
	}

	/**
	 * This method will close Add to Cart Pop Up
	 * @param NA
	 * @return void
	 */
	public void closeAddToCartPopup() {
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, qIAseqTargetedDNACustomPanelsPage.closeAddToCartPopUp, 10);
		qIAseqTargetedDNACustomPanelsPage.closeAddToCartPopUp.click();
	}

	/**
	 * This method will verify selected design on Configure Page
	 * @param NA
	 * @return void
	 */
	public void validateSelectedOutputDesignOnConfigurePage() {
		assertTrue(gapmeRSNameSelected.isDisplayed(),"Selected GapmeRs Name is not displayed on Design Configure Page.");
		assertTrue(gapmeRSNameDescriptionSelected.isDisplayed(),"Selected GapmeRs Description is not displayed on Design Configure Page.");
		assertTrue(resourceLink.isDisplayed(),"Resources link is not displayed on Design Configure Page.");
		assertTrue(productNoCatalogNoPricing.get(0).isDisplayed(),"Product No. is not displayed on Design Configure Page.");
		assertTrue(productNoCatalogNoPricing.get(1).isDisplayed(),"Catalog No is not displayed on Design Configure Page.");
		assertTrue(productNoCatalogNoPricing.get(2).isDisplayed(),"Price is not displayed on Design Configure Page.");	
	}

	/**
	 * This method will verify Control Page
	 * @param Scenarios
	 * @return void
	 */
	public void verifyControlPage(List<String> Scenarios) {
		CommonUtilities.configureWait("normalwait", 5);
		CommonUtilities.configureExplicitWait(CommonUtilities.WaitType.Visibility, designerName, 20);
		for (int iList=0; iList<Scenarios.size(); iList++) {
			switch(Scenarios.get(iList).trim()) {

			case "Breadcrumb":
				assertTrue(breadcrumb.isDisplayed(),"Breadcrumb is not displayed on Design Configure Page.");
				break;

			case "Designer Name as provided by the user and status as Design Complete":
				assertTrue(designerName.isDisplayed(),"Designer Name provided by the user is not displayed on Design control Page.");
				System.out.println(designerStatus.getText());
				assertTrue(designerStatus.getText().contains("design completed"),"Designer Status is not displayed as Design Completed on control Page.");
				break;

			case "Workflow steps":
				assertTrue(SelectControlsTab.isDisplayed(),"Workflow steps is not displayed on Design control Page.");
				break;

			case "Selected GapmeRs section with Gapmer count and Hide section option":
				assertTrue(SelectedGapmeRs.isDisplayed(),"Selected GapmeRs section is not displayed on Design Control Page.");
				assertTrue(ShowSelectedGapmerLink.isDisplayed(),"Selected GapmeRs Link is not displayed on Design Control Page.");
				break;

			case "Negative Controls section with count and Hide section option":
				assertTrue(negativeControlDesignCompleted.isDisplayed(),"Negative Control section is not displayed on Design Control Page.");
				assertTrue(showNegativeControlDesignCompleted.isDisplayed(),"Show Negative Control section Link is not displayed on Design Control Page.");
				break;

			case "Positive Controls section with count and Hide section option":
				assertTrue(positiveControlDesignCompleted.isDisplayed(),"Positieve Control section is not displayed on Design Control Page.");
				assertTrue(showPositiveControlDesignCompleted.isDisplayed(),"Show Positieve Control section Link is not displayed on Design Control Page.");
				break;

			case "Total Price , Add to Cart button and Can't order Online? link must be displayed at the bottom of the page.":
				assertTrue(totalPrice.isDisplayed(),"Total Price is not displayed on Design Control Page.");
				assertTrue(addToCartBtn.isDisplayed(),"Add to Cart button is not displayed on Design Control Page.");
				assertTrue(CantOrderOnline.isDisplayed(),"Can't order Online text is not displayed on Design Control Page.");
				break;

			case "Do you want to change your design? Reconfigure , Redesign":
				assertTrue(WouldLikeToChangeYourDesign.isDisplayed(),"Would You Like To Change Your Design is not displayed on Design Control Page.");
				assertTrue(reConfigureBtn.isDisplayed(),"Reconfigure Button is not displayed on Design Control Page.");
				assertTrue(reDesignBtn.isDisplayed(),"Redesign button is not displayed on Design Control Page.");
				break;
			}
		}

	}

	/**
	 * This method will verify Share Button
	 * @param NA
	 * @return void
	 */
	public void validateShareButton() {
		CommonUtilities.configureWait("normalwait", 5);
		assertTrue(btnShare.isDisplayed(),"Share Button is not available on Gapmer Control page");
	}

	/**
	 * This method will click Share Button
	 * @param NA
	 * @return void
	 */
	public void clickShareButton() {
		CommonUtilities.dynamicWait(btnShare, 10);
		btnShare.click();
	}

	/**
	 * This method will enter Email ID on Share Design Pop Up
	 * @param emailID
	 * @return void
	 */
	public void enterEmailID(String emailID) {
		CommonUtilities.configureWait("normalwait", 5);
		txtShareEmailID.click();
		txtShareEmailID.sendKeys(emailID);
	}

	/**
	 * This method will click Share Button
	 * @param NA
	 * @return void
	 */
	public void clickShareButtonOnSharingDesignForm() {
		btnShareEmail.click();
	}

	/**
	 * This method will verify Design Share Successful message
	 * @param NA
	 * @return void
	 */
	public void validateDesignShareSuccessfullMessage() {
		CommonUtilities.dynamicWait(btnDesignShareSuccessPopUpCross, 10);
		assertTrue(btnDesignShareSuccessPopUpCross.getTagName().length()>0,"Design Share Message is not available on Gapmer Control page");
		CommonUtilities.mouseClick(btnDesignShareSuccessPopUpCross);
	}

	/**
	 * This method will Share the Design Design
	 * @param EmailID
	 * @return void
	 */
	public void shareGapmerDesign(String EmailID) {
		clickShareButton();
		enterEmailID(EmailID);
		clickShareButtonOnSharingDesignForm();
		validateDesignShareSuccessfullMessage();
	}

}
