package Base_Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.taskdefs.Sleep;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class Base_Class {
	
	public static Properties prop;
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	Robot robot;
	
	
	
	public Base_Class() throws IOException
	{
		try {
			
			prop = new Properties();
			
			String File = System.getProperty("user.dir")+"\\src\\main\\java\\configuration\\configuration.properties";
			FileInputStream fp = new FileInputStream(File);
			prop.load(fp);
			
			
		}catch(NullPointerException e)
		{
			e.printStackTrace();
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void initialzation() throws InterruptedException
	{
		String browser = prop.getProperty("Browser_Name");
		if(browser.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\User1\\git\\Admin_panel_By_Tarun\\Admin_panel_new2\\browser_driver\\chromedriver_96.exe");
			driver = new ChromeDriver();
			
			
		}else if(browser.equals("FF"))
			{
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\User1\\git\\Admin_panel_By_Tarun\\Admin_panel_new2\\browser_driver\\geckodriver.exe");
				driver = new FirefoxDriver();
				
		}else if(browser.equals("Edge"))
			{
				System.setProperty(browser, browser);
					
			}else
			{
				System.out.println("browser is not defind on configuration.properties file ");
			}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URL"));
		
		
	}
	
	public void websiteload()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	public void pageload()
	{
		driver.manage().timeouts().pageLoadTimeout(300000, TimeUnit.MILLISECONDS);
	//	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	public void sleep() throws InterruptedException
	{
		Thread.sleep(5000);
	}
	
	public void refresh() throws InterruptedException
	{
		driver.navigate().refresh();
	}
	
	public void wait_upto_elementVisible(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void select_element_from_dropdown(WebElement dropdown,String value)
	{
		Select drpCountry = new Select(dropdown);
		drpCountry.selectByVisibleText(value);
	}
	
	public void robot(int Keybord_key) throws AWTException
	{
		robot = new Robot();
		robot.keyPress(Keybord_key);
		robot.keyRelease(Keybord_key);
	}
	
	
	public static void ExtentReports()
	{
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("Extenetreport.html");
		extent.attachReporter(spark);
	}
	
	public static void extentflush()
	{
		extent.flush();
	}
	
	
	public void Takescreenshot(String image) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("./Screenshots/"+image+".png"));
		System.out.println("the Screenshot is taken");
	}
	
	public void Execute_java_script(String Script)
	{
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript(Script);
	}
	
	public NgWebDriver getNGWebdriver()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (new NgWebDriver(js));
	}
	

}
