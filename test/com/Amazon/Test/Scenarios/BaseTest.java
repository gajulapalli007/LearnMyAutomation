package com.Amazon.Test.Scenarios;

import java.io.File;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.Amazon.Library.Need_Help;
import com.Amazon.Library.ShoppingCart;
import com.Amazon.Library.Utility;
import com.Amazon.Library.homePage;
import com.Amazon.Library.neerusignuppage;
import com.Amazon.Library.signInPage;

public class BaseTest {

	protected RemoteWebDriver driver;
	public static Utility util = new Utility();

	String chrome_path = util.getProp("WEBDRIVER_LOCATION")
			+ util.getProp("CHROME_DRIVER");
	String ie_path = util.getProp("WEBDRIVER_LOCATION")
			+ util.getProp("FIREFOX_DRIVER");

	protected homePage homePageObj;
	protected signInPage signInPageObj;
	protected Need_Help Need_HelpObj;
	protected ShoppingCart ShoppingCartObj;
	protected neerusignuppage neerusignuppageObj;
	// protected homePage homePageObj;

	/*
	 * protected StartPagina_kindzaken startPagina_kindzakenObj; protected
	 * Overzicht_Kindzaken overzicht_KindzakenObj; protected LoginPage loginObj;
	 * protected HomePage homePageObj; protected LoginPage logger; protected
	 * WiltAanmaken wiltAanmakenObj; protected ZoekenKind zoekenKindObj;
	 * protected CategoryPage categoryPageObj; protected ResultaatZoeken
	 * resultaatZoekenObj; protected SoortGBAPage soortGBAPageObj; protected
	 * OpvragenGBAPage opvragenGBAPageObj; protected ResultaatGBAPage
	 * resultaatGBAPageObj; protected GekoppeldePerson gekoppeldePersonObj;
	 * protected Gekoppelde2Person gekoppelde2PersonObj; protected
	 * Gekoppelde3Person gekoppelde3PersonObj; protected KindzaakStraf
	 * kindzaakStrafObj; protected DelictSelectionPage delictSelectionPageObj;
	 * protected WetsarikelPage wetsarikelPageObj; protected NogDelictPage
	 * nogDelictPageObj; protected OverzictAanMakenPage overzictAanMakenPageObj;
	 * protected FysiekDossier fysiekDossierObj; protected Question1Page
	 * question1PageObj; protected Question2Page question2PageObj; protected
	 * Question3Page question3PageObj; protected Question4Page question4PageObj;
	 * protected Question5Page question5PageObj;
	 */

	public Logger log = Logger.getLogger(this.getClass().getName());

	public void refreshPage() {
		driver.navigate().refresh();
	}

	// before class is executed
	@BeforeClass(alwaysRun = true)
	public void setupSelenium() throws InterruptedException {
		// org.apache.log4j.BasicConfigurator.configure();
		// org.apache.log4j.PropertyConfigurator.configur(file/C:\Users\RVDK\Documents\Selenium
		// POC\Libraries/log4j.properties)

		String browser = System.getProperty("BROWSER");
		if (browser == null) {
			browser = util.getProp("BROWSER");
		}
		// log.info("Browser Selected is: " + browser);
		if (browser.equalsIgnoreCase("Firefox")) {

			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("focusmanager.testmode", true);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			log.info("Firefox driver loaded successfully");
		} else if (browser.equalsIgnoreCase("Chrome")) {
			log.info("Chrome driver loading.....");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			File file = new File(chrome_path);
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());
			driver = new ChromeDriver(options);
			driver.manage().window().setPosition(new Point(0, 0));
			java.awt.Dimension screenSize = java.awt.Toolkit
					.getDefaultToolkit().getScreenSize();
			Dimension dim = new Dimension((int) screenSize.getWidth(),
					(int) screenSize.getHeight());
			driver.manage().window().setSize(dim);
			// pEditor = widgets.propertyEditor();
			log.info("Chrome driver loaded successfully");
		} else if (browser.equalsIgnoreCase("IE")) {
			log.info("IE driver loading....");
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			File file = new File(ie_path);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
			log.info("IE driver loaded successfully");
		} else if (browser.equalsIgnoreCase("safari")) {
			log.info("Safari driver loading....");
			driver = new SafariDriver();
			driver.manage().window().maximize();
			log.info("Safari driver loaded successfully");
		}
		util = new Utility(driver);
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() {
		try {
			log.info("Time Spent Waiting on Threads or Elements during Selenium Testing: "
					+ (util.getTotalTimeSpentWaiting() / 1000) + " seconds.");
			// driver.close(); // close will exit from the current browsers
			driver.quit();// quit will exit from all browsers and call dispose
							// on Webdriver, here we are cleaning up.
		} catch (Exception e) {
			// this is clean up so doesn't matter if it fails.
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void initialize(Method m) {
		log.info("--------------- Start:  " + m.getName()
				+ "  -------------------");
		log.info("");
		log.info("");
		log.info("Before method --::: 'initialize' :::--");
		util.waitForElement(1000);

		/*
		 * startPagina_kindzakenObj = PageFactory.initElements(driver,
		 * StartPagina_kindzaken.class); overzicht_KindzakenObj =
		 * PageFactory.initElements(driver, Overzicht_Kindzaken.class); loginObj
		 * = PageFactory.initElements(driver, LoginPage.class); homePageObj=
		 * PageFactory.initElements(driver, HomePage.class); wiltAanmakenObj=
		 * PageFactory.initElements(driver, WiltAanmaken.class); zoekenKindObj=
		 * PageFactory.initElements(driver, ZoekenKind.class);
		 * 
		 * logger = PageFactory.initElements(driver, LoginPage.class);
		 */
		homePageObj = PageFactory.initElements(driver, homePage.class);
		signInPageObj = PageFactory.initElements(driver, signInPage.class);
		Need_HelpObj = PageFactory.initElements(driver, Need_Help.class);
		ShoppingCartObj = PageFactory.initElements(driver, ShoppingCart.class);
		neerusignuppageObj = PageFactory.initElements(driver,
				neerusignuppage.class);
		// homePageObj = PageFactory.initElements(driver, homePage.class);

	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp(Method m) {
		util.waitForElement(200);
		log.info("After method --::: 'cleanUp' :::--");
		log.info("--------------- End:  " + m.getName()
				+ "  -------------------");
	}

}
