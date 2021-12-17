package Continue_Testing;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base_Package.*;
import website_pages.Dashboard_page;
import website_pages.Login_page;

public class Login_Testcases extends Base_Class {

	public Dashboard_page dash;
	public Login_page logpage;
	public ExtentTest test;
	
	public Login_Testcases() throws IOException {
		super();
		
	}
	
	@BeforeTest
	public void initExtentreport()
	{
		ExtentReports();
	}
	
	@BeforeMethod
	public void setup() throws Exception
	{
		initialzation();
		logpage = new Login_page();
	}
	
	@Test(priority=1)
	public void Verify_login() throws IOException, InterruptedException
	{
		try {
		test = extent.createTest("TC1_Verify Admin login");
		dash = logpage.login(prop.getProperty("Username"), prop.getProperty("Password"), prop.getProperty("Database"));
		test.log(Status.PASS, "Admin login Successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	
	@AfterTest
	public void flushextent()
	{
		extentflush();
	}
	
	

}
