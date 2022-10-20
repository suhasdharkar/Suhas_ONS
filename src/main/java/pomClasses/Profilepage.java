package pomClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilClasses.Util1;

public class Profilepage extends Util1{
	WebDriver driver;
	@FindBy (xpath="//div[text()='Manage Addresses']")
	private WebElement manageAddress;
	@FindBy (xpath="//div[@class='_1QhEVk']")
	private WebElement addNewAddress;
	@FindBy (xpath="//textarea[@tabindex='5']")
	private WebElement detailAddress;
	@FindBy (xpath="//button[text()='Save']")
	private WebElement saveButton;
	
	public Profilepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver= driver;
	}
	public void clickOnManageAddress() {
		manageAddress.click();
	}
	public void clickOnAddNewAddress() {
		addNewAddress.click();
	}
	public void addNewAdress(List<String> addAddress) throws InterruptedException {
		
		for(int i=1;i<=4;i++) {	
		driver.findElement(By.xpath("//input[@tabindex='"+i+"']")).sendKeys(addAddress.get(i-1));
		}
		detailAddress.sendKeys(addAddress.get(4));
		xplicitWait(driver, saveButton);
		saveButton.click();
		xplicitWait(driver, addNewAddress);
		clickOnAddNewAddress();
	}
}
