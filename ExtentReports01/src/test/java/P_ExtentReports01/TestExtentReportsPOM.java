package P_ExtentReports01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestExtentReportsPOM {
	static WebDriver driver;
	static WebElement identifiant, mot_de_passe, signin;

	public void lancer_lebrowser( String uRL_link) throws InterruptedException {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Utilisateur\\eclipse-workspace\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(uRL_link);
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
	public void fermer () {driver.quit();}

}
