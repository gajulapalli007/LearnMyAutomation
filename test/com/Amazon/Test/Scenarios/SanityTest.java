/**
 * 
 */
package com.Amazon.Test.Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Owner
 *
 */
public class SanityTest {
	public static void main(String[] args) throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Users\\Owner\\selenium for beigners\\MyTestAutomation\\lib\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://amazon.co.uk");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.id("nav-link-yourAccount")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("ap_email")).sendKeys(
				"archana.gajulapalli1128@gmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("auth-fpp-link-bottom")).click();

	}
}