package P_ExtentReports01;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class C_ExtentReports01 {

	
	static WebDriver driver;
	static ExtentReports extent;
	static ExtentTest ExtentTest;
	WebElement champRechercher, champ_Saisie, identifiant, mot_de_passe, signin;
	
//contructeur
public C_ExtentReports01() { }
		
		@BeforeTest
		static void setExtent() {
		extent = new ExtentReports (System.getProperty("user.dir")+"\\Test-output\\ExtentReport.html", true);
		extent.addSystemInfo("User Name", "Mustapha ECH_Autom");
		extent.addSystemInfo("Environment", "UAT - SIT01");
								}
		

	
		// this is a destination that what these method is a string
		public static String getscreenshot (WebDriver driver, String screenshotName) throws IOException 
		{ 
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			// after execution, you can see a folder "FailedTestsScreenshots"
			// under src folder
			String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		}
		@BeforeMethod
		public void setup() {
			//String chemin = System.getProperty("user.dir");
			//System.setProperty("webdriver.gecko.driver", chemin+"\\Drivers\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Utilisateur\\eclipse-workspace\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.get("https://www.youtube.com/watch?v=D4FkhMrO9fw");
							}
		@Test
		public void test_Case01 () {
			ExtentTest = extent.startTest("test_Case01");
			driver.get("https://www.youtube.com/watch?v=D4FkhMrO9fw");
			String title = driver.getTitle();
			System.out.println(title);
			if (title.equalsIgnoreCase("How to add Screenshot in Extent Report for Failed Test Cases in Selenium - YouTube")) {System.out.println("TestCase01 : OK");}
			else {System.out.println("TestCase01 : KO");}
			//Assert.assertEquals(title, "How to add Screenshot in Extent Report for Failed Test Cases in Selenium - YouTube");
									}
		@Test
		public void test_Case02 () {
			ExtentTest = extent.startTest("test_Case02");
			driver.get("https://www.youtube.com/");
			String title = driver.getTitle();
			System.out.println(title);
			Assert.assertEquals(title, "YouTube");
									}

		@Test 
		public void test_Case03 () {
			ExtentTest = extent.startTest("test_Case03");
			int x = 51;
			int y = 50;
			if (x==y) 
			
			{ System.out.println("Yes");}
			else {System.out.println("No");}
									}
		
		@Test 
		public void test_Case04 () {
			ExtentTest = extent.startTest("test_Case04");
			int X = 60;
			int Y = 50;
			if (Y<X)
			{System.out.println("Y is less than X");}
			else {System.out.println("Y is greather then X");}
									}
		@Test
		public void test_Case05 () {
			ExtentTest = extent.startTest("test_Case05");
			int time = 18;
		
			if (time<10)	{System.out.println("Bonjour");}
			else if (time < 19)	{System.out.println("Bonne journée");}
			else {System.out.println("Bonne soirée");}
									}
		
		@Test
		public void test_Case06 () throws InterruptedException {
			ExtentTest = extent.startTest("test_Case06");
			driver.get("https://www.youtube.com/");
			champRechercher = driver.findElement(By.id("search-icon-legacy"));
			System.out.println("boutton champ_Saisie : trouvé ! " + champ_Saisie.isDisplayed());
			Thread.sleep(5000);
			champ_Saisie= driver.findElement(By.xpath("//input[@id='search']"));
			System.out.println("boutton champ_Rechercher : trouvé ! " + champRechercher.isDisplayed());
			Thread.sleep(5000);
			champ_Saisie.sendKeys("POM : selenium");
			Thread.sleep(5000);
			// erreur faite exprès
			champRechercher.sendKeys("POM : selenium");
			Thread.sleep(5000);
																}
		
		@Test
		public void test_Case07() throws InterruptedException {
			ExtentTest = extent.startTest("test_Case07");
			driver.get("https://intra.extia.fr/login/");
			identifiant = driver.findElement(By.name("user_login"));
			System.out.println("ID button found : "+identifiant.isDisplayed());
			Thread.sleep(3000);
			identifiant.sendKeys("mechchyguer");
			mot_de_passe = driver.findElement(By.xpath("//input[@name='user_password']"));
			System.out.println("Password button found : "+mot_de_passe.isDisplayed());
			mot_de_passe.sendKeys("Mu$t@ph@1990");
			Thread.sleep(3000);
			signin = driver.findElement(By.className("form2"));
			System.out.println("signin button found : "+signin.isDisplayed());
			signin.click();
																}
			
		@AfterMethod
		public void tearDown(ITestResult result) throws IOException 
						{
			if (result.getStatus()==ITestResult.FAILURE) 
			{
				ExtentTest.log(LogStatus.FAIL, "TestCase Failed is : "+result.getName());	// to add name in extent report
				ExtentTest.log(LogStatus.FAIL, "TestCase Failed is : "+result.getThrowable());	// to add error/exception in extent report
				String screenshotPath = getscreenshot(driver, result.getName());
				ExtentTest.log(LogStatus.FAIL, ExtentTest.addScreenCapture(screenshotPath));	// to add screen in extent report
				//ExtentTest.log(LogStatus.FAIL, ExtentTest.addScreencast(screenshotPath));	// to add screen/video in extent report		
			}
			else if (result.getStatus()==ITestResult.SKIP) {ExtentTest.log(LogStatus.SKIP, "Test Case Skipped is : "+result.getName());}
			
			else if (result.getStatus()==ITestResult.SUCCESS) {ExtentTest.log(LogStatus.PASS, "Test Case Passed is : "+result.getName());}
			
			extent.endTest(ExtentTest); // ending test and ends the current test and prepare to create html report
			driver.quit();}
		
		@AfterTest
		public void endReport() {
			// close the connection with extent reports
			extent.flush();
			extent.close();
								}
}
