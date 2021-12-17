package website_pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.paulhammant.ngwebdriver.NgWebDriver;

import Base_Package.Base_Class;

public class Login_page extends Base_Class {
	
	Robot robot;
	Select select;
	
	@FindBy(xpath="/html/body/app-root/div/div/app-login/section/div/div/div/div[1]/img")
	public
	WebElement logo;
	
	@FindBy(xpath = "/html/body/app-root/app-signin/div/div[2]/div/div/div/form")
	WebElement form;
	
	@FindBy(id="email")
	WebElement user_name;

	@FindBy(id="password")
	WebElement Password;
	
	@FindBy(css="kendo-dropdownlist.form-control.form-control-sm.k-widget.k-dropdown.k-header.ng-untouched.ng-pristine.ng-valid")
	WebElement Company_dropdown;
	
	@FindBy(css="button.k-button.k-primary")
	WebElement Submit;
	
	@FindBy(xpath="/html/body/app-root/app-signin/kendo-dialog/div[2]")
	WebElement confirmpopup;
	
	@FindBy(xpath="/html/body/app-root/app-signin/kendo-dialog/div[2]/kendo-dialog-actions/button[1]")
	WebElement yes;
	
	
	public Login_page() throws IOException, AWTException {
		super();
		PageFactory.initElements(driver, this);
		robot = new Robot();
		
	}
	
	public Dashboard_page login(String username, String password,String database) throws InterruptedException
	{
		user_name.sendKeys(username);
		sleep();
		Password.sendKeys(password);
		sleep();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		sleep();
		//Company_dropdown.click();
				
		/*
		 * select = new Select(Company_dropdown); select.deselectAll(); //
		 * select.selectByIndex(3); select.selectByValue(database); //
		 * select.selectByVisibleText(database);
		 */		
		 
		List<WebElement> value = Company_dropdown.findElements(By.xpath("/html/body/app-root/app-signin/div/div[2]/div/div/div/form/label[3]/kendo-dropdownlist/span"));
		
		 System.out.print(value.size());
		
		 for (int i = 0; i < value.size(); i++) {
			
			 String temp = value.get(i).getText();
		        if (temp.equals(database)) {
		        	value.get(i).click();             
		            break;
		 }}

		 sleep();
		Submit.click();
		sleep();
		driver.switchTo().activeElement();
		if(confirmpopup.isDisplayed())
		{
			yes.click();
		}
		sleep();
		return new Dashboard_page();
	}

	
	
	
}


