package testClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClasses.Base;
import pomClasses.Homepage;
import pomClasses.Profilepage;
import utilClasses.Util1;

public class VerifyUserCanAddAddress extends Util1{
	WebDriver driver;
	Homepage hp;
	Profilepage pp;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser){
		driver=Base.webdriverStarting(browser);
	}
	@BeforeMethod
	public void beforeMethod() {
		hp=new Homepage(driver);
		pp= new Profilepage(driver);
	}
	@Test(priority=1)
	public void userCanGoToMyProfile() {
		if(
				hp.clickOnMyProfile()
				) {
			System.out.println("Test is passed");
		}else {
			System.out.println("Test is failed");
		}
		pp.clickOnManageAddress();
		pp.clickOnAddNewAddress();
	}
	
	@DataProvider(name="testdata")
		public String[][] getData() {
			String[][] uData= {{"Suhas","9923833118","414701","Near Z.P.School Dharkarwadi","Chimbhale, Tal-Shrigonda"},
					{"Rohini","9865511256","412207","Near I max Hospital","Wagholi,Pune"}};
			System.out.println(uData);
			return uData;
		}
	
	@Test(dataProvider="testdata",priority=2)
	public void UserCanGetAddress(String Name,String MobNumber,String PinCode,String Locality,String DetailAdress) throws InterruptedException {
		
		List<String> adressDataList= new ArrayList<>(Arrays.asList(Name,MobNumber,PinCode,Locality,DetailAdress));
		
		pp.addNewAdress(adressDataList);
		//System.out.println(adressDataList);
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println();
	}
	@AfterClass
	public void afterClass() {
		Base.unloadDriver();
	}

}
