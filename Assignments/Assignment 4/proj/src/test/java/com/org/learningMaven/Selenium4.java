package com.org.learningMaven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class Selenium4 {
	Properties p = new Properties();
	public static WebDriver d;
	public static Navigation n;

//loading the properties file
	@BeforeClass
	public void loadProperties() {
		try {
			p.load(new FileInputStream("/Users/yitongliu/eclipse-workspace/proj/config.properties"));
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Login to MyNeu")
	@Feature("Feature3: Login to Univ portal")
	@Story("Story: Valid login")
	@Step("Verify Login to MyNeu")
	public void testLoginNE() throws Exception {
		// Setting the driver properties
		System.setProperty("webdriver.chrome.driver", "/Users/yitongliu/Desktop/chromedriver");
		d = new ChromeDriver();
		d.manage().window().maximize();
		// navigating to nu login page
		d.get("https://my.northeastern.edu/");
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Login/page.png");
		// Login
		d.findElement(By.xpath(
				"//*[@id=\'portlet_com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_GhAIpHlwoE3O\']/div/div/div/div[2]/div/div[2]/div/a"))
				.click();
		d.findElement(By.xpath("//*[@id=\'username\']")).sendKeys(p.getProperty("username"));
		d.findElement(By.xpath("//*[@id=\'password\']")).sendKeys(p.getProperty("password"));
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Login/login.png");
		String path = "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Login/login.png";
		Reporter.log("<img width='400' height='240' src = '" + path + "'/><br/>");
		d.findElement(By.name("_eventId_proceed")).click();
		// push notification
		d.switchTo().frame("duo_iframe");
		d.findElements(By.xpath("//*[@id=\'auth_methods\']/fieldset/div[1]/button")).get(0).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Login/success.png");
		Thread.sleep(5000);
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Login/logged.png");
		String actualUrl = d.getCurrentUrl();
		String expectedUrl = "https://my.northeastern.edu";
		Assert.assertEquals(true, actualUrl.contains(expectedUrl));
		Reporter.log("testLoginNE: ");
		Reporter.log("Expected: " + expectedUrl);
		Reporter.log("Actual: " + actualUrl);
		Reporter.log("Fail/Pass: ");
		if (actualUrl.contains(expectedUrl))
			Reporter.log("Pass");
		else
			Reporter.log("Fail");

	}

	@Test(priority = 2)
	@Severity(SeverityLevel.MINOR)
	@Description("Add an item to favorites")
	@Feature("Feature4: Add to favorites")
	@Story("Story: bookmark")
	@Step("Add to favorites")
	public void testAddFav() throws Exception {
		Thread.sleep(12000);
		// search for text and add to favorites
		// search for course registration
		d.findElement(By.xpath("//*[@id=\'app-search-list\']/div/input")).sendKeys("husky");
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Add/husky.png");
		Thread.sleep(1000);
		d.findElements(By.xpath("//*[@id=\'app-search-list\']/div/div/div/div[1]/div/div[3]/i")).get(0).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Add/get1.png");
		d.findElements(By.xpath("//*[@id=\"app-search-list\"]/div/div/div/div[4]/div/div[3]/i")).get(0).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Add/get2.png");
		d.findElement(By.xpath("//*[@id=\'app-search-list\']/div/a")).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Add/Success.png");
		String path = "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/My_Favorites/Add/Success.png";
		Reporter.log("<img width='400' height='240' src = '" + path + "'/><br/>");
		Assert.assertEquals(d.getPageSource().contains("Husky Card Account Transactions"), true);
		Reporter.log("testAddFav: ");
		Reporter.log("Expected: " + "true");
		Reporter.log("Actual: " + d.getPageSource().contains("Husky Card Account Transactions"));
		Reporter.log("Fail/Pass: ");
		if (d.getPageSource().contains("Husky Card Account Transactions"))
			Reporter.log("Pass");
		else
			Reporter.log("Fail");
		Thread.sleep(4000);
		d.quit();
	}

	@Test(priority = 3)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Login to Bookstore")
	@Feature("Feature1: Login")
	@Story("Story:Valid Login")
	@Step("Verify Login")
	public void testLogin() throws Exception {
		// Setting the driver properties
		System.setProperty("webdriver.chrome.driver", "/Users/yitongliu/Desktop/chromedriver");
		d = new ChromeDriver();
		d.manage().window().maximize();
		// navigating to nu bookstore login page
		d.get("https://securex.bncollege.com/webapp/wcs/stores/servlet/BNCBLogonForm?catalogId=10001&langId=-1&storeId=17555&prevPage=LogonForm&disableGuestLogin=&ordItmMinusFreeDgItmCount=&postLogonURL=OrderCalculateMC%3FprevPage%3DLogonForm%26URL%3DBNCBMyAccountView%3FcatalogId%3D10001%26langId%3D-1%26storeId%3D17555");
		// call the function
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/Logincart/page.png");
		// Login
		d.findElement(By.xpath("//*[@id=\'logonId\']")).sendKeys(p.getProperty("UID"));
		d.findElement(By.xpath("//*[@id=\'logonPassword\']")).sendKeys(p.getProperty("PWD"));
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/Logincart/login.png");
		String path = "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/Logincart/login.png";
		Reporter.log("<img width='400' height='240' src = '" + path + "'/><br/>");
		d.findElement(By.name("login")).click();
		Reporter.log("testLogin: ");
		Reporter.log("Expected: " + "true");
		Reporter.log("Actual: " + d.getPageSource().contains("Yitong"));
		Reporter.log("Fail/Pass: ");
		if (d.getPageSource().contains("Yitong"))
			Reporter.log("Pass");
		else
			Reporter.log("Fail");

	}

	@Test(priority = 4)
	@Severity(SeverityLevel.MINOR)
	@Description("Verify addition of items to cart")
	@Feature("Feature2: Add to cart")
	@Story("Story: Shopping")
	@Step("Add to cart")
	public void testCart() throws Exception {
		Thread.sleep(2000);
		// searching the product
		WebElement searchElement = d.findElement(By.name("search"));
		Thread.sleep(1000);
		searchElement.sendKeys("fitbit");
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/testcart/product.png");
		Thread.sleep(2000);
		d.findElement(By.className("searchbutton")).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/testcart/result.png");
		d.findElement(By.xpath("//*[@id=\'foo1\']/li/a[1]/img")).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/testcart/select.png");
		Thread.sleep(4000);
		// add to cart
		d.findElement(By.id("addToCartId")).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/testcart/add.png");
		Thread.sleep(3000);
		// view cart
		d.findElement(By.xpath("/html/body/div[5]/div[3]/div/button[2]/span")).click();
		Thread.sleep(2000);
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/testcart/success.png");
		String path = "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/bookstore/testcart/success.png";
		Reporter.log("<img width='400' height='240' src = '" + path + "'/><br/>");
		String ActualTitle = d.getTitle();
		String expectedTitle = "Shopping Cart | The Northeastern University Bookstore";
		Reporter.log("testCart: ");
		Reporter.log("Expected: " + expectedTitle);
		Reporter.log("Actual: " + ActualTitle);
		Reporter.log("Fail/Pass: ");
		if (ActualTitle.equals(expectedTitle))
			Reporter.log("Pass");
		else
			Reporter.log("Fail");
		Assert.assertEquals(expectedTitle, ActualTitle);
		Thread.sleep(2000);
		d.quit();

	}

	@Test(priority = 5)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Login to MyNeu")
	@Feature("Feature3: Login to Univ portal")
	@Story("Story: Valid login")
	@Step("Verify Login to MyNeu")
	public void testLoginNECourse() throws Exception {
		// Setting the driver properties
		System.setProperty("webdriver.chrome.driver", "/Users/yitongliu/Desktop/chromedriver");
		d = new ChromeDriver();
		d.manage().window().maximize();
		// navigating to neu login page
		d.get("https://my.northeastern.edu/");
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/Login/page.png");
		// Login
		d.findElement(By.xpath(
				"//*[@id=\'portlet_com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_GhAIpHlwoE3O\']/div/div/div/div[2]/div/div[2]/div/a"))
				.click();
		d.findElement(By.xpath("//*[@id=\'username\']")).sendKeys(p.getProperty("uCourse"));
		d.findElement(By.xpath("//*[@id=\'password\']")).sendKeys(p.getProperty("pCourse"));
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/Login/login.png");
		String path = "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/Login/login.png";
		Reporter.log("<img width='400' height='240' src = '" + path + "'/><br/>");
		d.findElement(By.name("_eventId_proceed")).click();
		// push notification
		d.switchTo().frame("duo_iframe");
		d.findElements(By.xpath("//*[@id=\'auth_methods\']/fieldset/div[1]/button")).get(0).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/Login/success.png");
		Thread.sleep(5000);
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/Login/logged.png");
		String actualUrl = d.getCurrentUrl();
		String expectedUrl = "https://my.northeastern.edu";
		Assert.assertEquals(true, actualUrl.contains(expectedUrl));
		Reporter.log("testLoginNE: ");
		Reporter.log("Expected: " + expectedUrl);
		Reporter.log("Actual: " + actualUrl);
		Reporter.log("Fail/Pass: ");
		if (actualUrl.contains(expectedUrl))
			Reporter.log("Pass");
		else
			Reporter.log("Fail");

	}
	

	@Test(priority = 6)
	@Severity(SeverityLevel.NORMAL)
	@Description("Browse the class")
	@Feature("Feature5: Browse class")
	@Story("Story: Browse the classes")
	@Step("Browse Class")
	public void testCourse() throws Exception {
//		testLoginNE();
		Thread.sleep(4000);
		// search for course registration
		d.findElement(By.xpath("//*[@id=\'app-search-list\']/div/input")).sendKeys("course registration");
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/course.png");
		Thread.sleep(1000);
		d.findElements(By.xpath("//*[@id=\'app-search-list\']/div/div/div/div[1]/div/div[2]/a")).get(0).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/get.png");
		d.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		// browse classes
		Set<String> windowHandles = d.getWindowHandles();
		for (String winHandle : d.getWindowHandles()) {
			d.switchTo().window(winHandle);
		}
		d.navigate().to("https://nubanner.neu.edu/StudentRegistrationSsb/ssb/term/termSelection?mode=search");
		// term selection
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/termselection.png");
		d.findElement(By.id("s2id_txt_term")).click();
		String a = d.findElement(By.xpath("//*[@id=\"202130\"]")).getText();
		d.findElement(By.xpath("//*[@id=\'202130\']")).click();
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/spring.png");
		d.findElement(By.xpath("//*[@id=\'term-go\']")).click();
		// search for course
		d.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		d.findElement(By.cssSelector("#s2id_txt_subject > .select2-choices")).click();
		d.findElement(By.id("s2id_autogen1")).sendKeys("Information Systems Program");
		d.findElement(By.id("s2id_autogen1")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		d.findElement(By.xpath("//*[@id=\"INFO\"]")).click();
		;
		d.findElement(By.xpath("//*[@id=\'search-go\']")).click();
		Thread.sleep(2000);
		String semester = d.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[7]")).getText();
		Thread.sleep(2000);
		this.takeSnapShot(d, "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/sub.png");
		String path = "/Users/yitongliu/eclipse-workspace/proj/Seleniumproj/course/sub.png";
		Reporter.log("<img width='400' height='240' src = '" + path + "'/><br/>");
		Reporter.log("testCourse");
		Reporter.log("Expected: " + a);
		Reporter.log("Actual: " + semester);
		Assert.assertEquals(a, semester, "Same Result");
		Reporter.log("Pass/Fail: ");
		if (a.equals(semester))
			Reporter.log("Pass");
		else
			Reporter.log("Fail");
		Thread.sleep(2000);
		d.quit();

	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}

}