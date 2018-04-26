package com.Amazon.Library;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Utility {

	private WebDriver driver;

	private long defaultTimeOut = 3000; // milliseconds
	private long defaultMaxWait = 240000; // milliseconds
	private static long totalTimeSpentWaiting; // used to calculate the wait
	// time of elements/sleeps
	// (milliseconds)

	public Logger log = Logger.getLogger(this.getClass().getName());

	public Utility() {
	}

	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	public String getProp(String propertyName) {
		String propertyValue = null;
		try {
			Properties prop = new Properties();
			prop.load(getClass().getClassLoader().getResourceAsStream(
					"data.properties"));
			// prop.load(new
			// FileInputStream("/Users/212308030/workspace/studio/src/test/resources/widget.properties"));
			propertyValue = prop.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}

	public long getTotalTimeSpentWaiting() {
		return totalTimeSpentWaiting;
	}

	public void waitForElement(String elementIdentifier, Object... millis) {
		waitForElement("id", elementIdentifier, millis);
	}

	/* (javadoc? move to Utility?) */
	// uses explicit selenium which will wait on a dom element id
	// optionally you may pass a minimum and maximum wait time.
	public void waitForElement(String IdentifierType, String elementIdentifier,
			Object... millis) {
		if (millis.length > 0) {
			waitForElement(millis);
		}

		long maxWait = millis.length > 1 ? (Long) millis[1] : defaultMaxWait;
		long maxWaitInSeconds = maxWait / 1000;

		long startTime = System.currentTimeMillis();
		try {
			WebDriverWait wait = new WebDriverWait(this.driver,
					maxWaitInSeconds);
			if (IdentifierType.trim().equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.name(elementIdentifier)));
			} else if (IdentifierType.trim().equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.id(elementIdentifier)));
			} else if (IdentifierType.trim().equalsIgnoreCase("class")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.className(elementIdentifier)));
			}
		} catch (Exception e) {
			log.warn("Could not wait for WebElement, threw the following error: "
					+ e.getMessage());
		}
		long endTime = System.currentTimeMillis();

		totalTimeSpentWaiting += (endTime - startTime);
	}

	/**
	 * 
	 */
	public void handlePopup() {
		/* Handle Popup as Alert */
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		String enter = Keys.chord(Keys.SPACE);
		alert.sendKeys(enter);
		alert.accept();
	}

	public void waitForElement(WebElement element, Object... millis) {
		waitForVisibilityOf(element, millis);
	}

	public void waitForElement(Object... millis) {
		long timeout = defaultTimeOut;
		if (millis.length > 0) {
			timeout = Long.parseLong(String.valueOf(millis[0]));
			try {
				Thread.sleep(timeout);
			} catch (Exception e) {
			}
		} else {
			try {
				Thread.sleep(timeout);
			} catch (Exception e) {
			}
		}
		totalTimeSpentWaiting += timeout;
	}

	// similar to waitForElement but waits for element to be "click-able"
	public void waitForElementToBeClickable(String elementID, Object... millis) {

		if (millis.length > 0) {
			waitForElement(millis);
		}
		long maxWait = millis.length > 1 ? (Long) millis[1] : defaultMaxWait;
		long maxWaitInSeconds = maxWait / 1000;

		long startTime = System.currentTimeMillis();
		try {
			WebDriverWait wait = new WebDriverWait(driver, maxWaitInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
		} catch (Exception e) {
			log.warn("Could not wait for WebElement, threw the following error: "
					+ e.getMessage());
		}
		long endTime = System.currentTimeMillis();

		totalTimeSpentWaiting += (endTime - startTime);
	}

	public void waitForVisibilityOf(String IdentifierType,
			String elementIdentifier, Object... millis) {

		if (millis.length > 0) {
			waitForElement(millis);
		}

		long maxWait = millis.length > 1 ? (Long) millis[1] : defaultMaxWait;
		long maxWaitInSeconds = maxWait / 1000;

		try {
			WebDriverWait wait = new WebDriverWait(this.driver,
					maxWaitInSeconds);
			if (IdentifierType.trim().equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.visibilityOf(driver
						.findElement(By.name(elementIdentifier))));
			} else if (IdentifierType.trim().equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.visibilityOf(driver
						.findElement(By.id(elementIdentifier))));
			} else if (IdentifierType.trim().equalsIgnoreCase("class")) {
				wait.until(ExpectedConditions.visibilityOf(driver
						.findElement(By.className(elementIdentifier))));
			}
		} catch (Exception e) {
			log.warn("Could not wait for WebElement, threw the following error: "
					+ e.getMessage());
		}
	}

	public void waitForInVisibilityOf(String IdentifierType,
			String elementIdentifier, Object... millis) {

		if (millis.length > 0) {
			waitForElement(millis);
		}

		long maxWait = millis.length > 1 ? (Long) millis[1] : defaultMaxWait;
		long maxWaitInSeconds = maxWait / 1000;

		try {
			WebDriverWait wait = new WebDriverWait(this.driver,
					maxWaitInSeconds);
			if (IdentifierType.trim().equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.name(elementIdentifier)));
			} else if (IdentifierType.trim().equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id(elementIdentifier)));
			} else if (IdentifierType.trim().equalsIgnoreCase("class")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.className(elementIdentifier)));
			}
		} catch (Exception e) {
			log.warn("Could not wait for WebElement to disappear, threw the following error: "
					+ e.getMessage());
		}
	}

	public void waitForVisibilityOf(WebElement element, Object... millis) {
		if (millis.length > 0) {
			waitForElement(millis);
		}

		long maxWait = millis.length > 1 ? (Long) millis[1] : defaultMaxWait;
		long maxWaitInSeconds = maxWait / 1000;

		long startTime = System.currentTimeMillis();
		try {
			(new WebDriverWait(driver, maxWaitInSeconds))
					.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			log.warn("Could not wait for WebElement, threw the following error: "
					+ e.getMessage());
		}
		long endTime = System.currentTimeMillis();

		totalTimeSpentWaiting += (endTime - startTime);
	}

	/*
	 * public static String[][] readXls(String xlspath, String sheetname) throws
	 * IOException { // Excel file path File testDataFile = new File(xlspath);
	 * // Define Input Stream FileInputStream fis = new
	 * FileInputStream(testDataFile); // Create Workbook handler HSSFWorkbook
	 * wBook = new HSSFWorkbook(fis); // Sheet handler HSSFSheet sheet =
	 * wBook.getSheet(sheetname); // No of rows in the Sheet int rowNum =
	 * sheet.getLastRowNum() + 1; // No of Columns in the Sheet int colNum =
	 * sheet.getRow(0).getLastCellNum(); // Array to represent the Sheet Data
	 * String[][] testData = new String[rowNum][colNum];
	 * 
	 * for (int i = 0; i < rowNum; i++) { // Get Row HSSFRow row =
	 * sheet.getRow(i);
	 * 
	 * for (int j = 0; j < colNum; j++) { // Get Heading and keep Looping for
	 * rest of rows HSSFCell cell = row.getCell(j);
	 * 
	 * // Method call to convert Cell content to String String cellValue =
	 * valToString(cell); // Storing returned content to an Array testData[i][j]
	 * = cellValue; // Apply logic to use data e.g. print on console
	 * 
	 * // System.out.println("Data: "+cellValue);
	 * 
	 * } } fis.close(); return (testData); }
	 * 
	 * private static String valToString(HSSFCell cell) { // TODO Auto-generated
	 * method stub int type; Object obj;
	 * 
	 * type = cell.getCellType(); switch (type) {
	 * 
	 * case 0: obj = cell.getNumericCellValue(); break; case 1: obj =
	 * cell.getStringCellValue(); break; default: throw new
	 * RuntimeException("Cell Type not known");
	 * 
	 * }
	 * 
	 * // Return Object return obj.toString(); }
	 */

	protected WebElement waitAndFindElement(SearchContext context, final By by,
			long timeoutSeconds, long sleepMilliseconds) {
		FluentWait<SearchContext> wait = new FluentWait<SearchContext>(context)
				.withTimeout(timeoutSeconds, TimeUnit.SECONDS)
				.pollingEvery(sleepMilliseconds, TimeUnit.MILLISECONDS)
				.ignoring(NotFoundException.class)
				.ignoring(NoSuchElementException.class);
		WebElement element = null;
		try {
			element = wait.until(new Function<SearchContext, WebElement>() {
				public WebElement apply(SearchContext context) {
					return context.findElement(by);
				}
			});
		} catch (TimeoutException te) {
			element = null;
		}
		return element;
	}

	protected WebElement waitAndFindElement(SearchContext context, By by) {
		return waitAndFindElement(context, by, DEFAULT_TIMEOUT,
				DEFAULT_POLL_SLEEP);
	}

	static long DEFAULT_TIMEOUT = 5; // seconds
	static long DEFAULT_POLL_SLEEP = 500; // milliseconds

	public void switchToCanvasFrame() {
		log.info("---- : - propEdit.switchToCanvasFrame: Switching to the frame 'canvas-frame'");
		driver.switchTo().defaultContent();
		waitForVisibilityOf(driver.findElement(By.id("canvas-iframe")));
		driver.switchTo().frame(driver.findElement(By.id("canvas-iframe")));
	}

	public void waitForVisibilityWithTimeout(WebElement element,
			int timeoutSeconds) {
		long startTime = System.currentTimeMillis();
		try {
			(new WebDriverWait(driver, timeoutSeconds))
					.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			log.warn("Could not wait for WebElement, threw the following error: "
					+ e.getMessage());
		}
		long endTime = System.currentTimeMillis();

		totalTimeSpentWaiting += (endTime - startTime);
	}

	public void Movetoelement(WebElement Element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(Element);
		actions.perform();
	}

}