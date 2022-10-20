package pomClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilClasses.Util1;

public class Homepage extends Util1 {
	WebDriver driver;
	@FindBy(xpath = "//div[text()='Suhas']")
	private WebElement profileName;
	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchProduct;
	@FindBy(xpath = "(//div[@class=\"_2kHMtA\"])[1]")
	private WebElement firstProduct;
	@FindBy(xpath = "//div[@class='_30jeq3 _1_WHN1']")
	private List<WebElement> elementListofProductsPrice;
	@FindBy(xpath = "(//li[@class='_2NOVgj'])[1]")
	private WebElement profileName1;
	@FindBy(xpath = "//div[text()='Hello']")
	private WebElement hello;

	public Homepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public boolean visibilityOfProfile() {
		xplicitWait(driver, By.xpath("//div[text()='Suhas']"));
		String Sw = profileName.getText();
		System.out.println(Sw);
		return true;
	}

	public void searchProduct() {
		searchProduct.sendKeys("vivo");
		searchProduct.sendKeys(Keys.ENTER);
		xplicitWait(driver, firstProduct);
	}

	public int getLowestPrice() {
		List<Integer> productPriceList = new ArrayList<>();
		for (WebElement k : elementListofProductsPrice) {
			productPriceList.add(Integer.parseInt(k.getText().replace("₹", "").replace(",", "")));
		}
		Collections.sort(productPriceList);
		return productPriceList.get(0);
	}

	public void switchPage(int i) {
		driver.findElement(By.xpath("//a[@class='ge-49M' and text()=" + i + "]")).click();
		xplicitWait(driver, By.xpath("//a[@class='ge-49M _2Kfbh8' and text()=" + i + "]"));
	}

	public boolean clickOnMyProfile() {

		move2Element(driver, profileName);
		profileName1.click();
		try {
			xplicitWait(driver, By.xpath("//span[text()='Personal Information']"));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
