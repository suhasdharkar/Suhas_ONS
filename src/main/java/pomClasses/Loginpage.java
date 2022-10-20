package pomClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilClasses.Util1;

public class Loginpage extends Util1{
	WebDriver driver;
	
	@FindBy(xpath="(//input[@autocomplete='off'])[2]")
	private WebElement emailField;							//Alternate methods
	
	@FindBy(xpath="(//input[@autocomplete='off'])[3]")     //private By password=By.xpath("(//input[@autocomplete='off'])[3]");
			private WebElement password;
					
	@FindBy(xpath="(//span[text()='Login'])[2]")     //private By loginbtn=By.xpath("(//span[text()='Login'])[2]");
	private WebElement loginBtn;
					
	public Loginpage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		
		this.driver=driver;
	}
	public void enteremailID() throws IOException {
		emailField.sendKeys(fileConfig("email"));
		}
	public void enterpassword() throws IOException {
		password.sendKeys(fileConfig("password"));																//driver.findElement(password).sendKeys("Akshay123");
	}
	public void Dologin() {
		loginBtn.click();													//driver.findElement(loginbtn).click();
	}
	

}
