package pom;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base_Class;

public class Login_page extends Base_Class {

	@FindBy(xpath = "//input[@id='loginkey']")
	public WebElement Username;
	@FindBy(xpath = "//input[@id='password']")
	public WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement sign_in_button;
	
	//Main page of the ERP
	@FindBy(xpath = "//a[normalize-space()='Issues']")
	public WebElement issues;
	//issue tap	
	@FindBy(xpath = "//select[@name='requesttypeid']")
	public WebElement Fillterby_requesttype;
	@FindBy(xpath = "//input[@title=' Req No/PPN/APN/Prd']")
	public WebElement search_by;
	//MRI request
	@FindBy(xpath = "(//button[@type='button'])[4]")
	public WebElement serach_button;	
	@FindBy(xpath = "(//button[@type='button'])[8]")
	public WebElement Edit;
	
	
	@FindBy(xpath = "(//div[@class='ng-star-inserted'])[9]")
	public WebElement PPN_search;
	

	@FindBy(xpath = "//input[@placeholder=' PPN Search']")
	public WebElement search_Box;
	@FindBy(xpath = "//span[normalize-space()='SVN ENGG BOM']")
	public WebElement SVN_ENG_BOM;
	@FindBy(xpath = "//span[@class='ui-multiselect-label ui-corner-all']")
	public WebElement Bomtype;
	@FindBy(xpath = "//span[text()='JIRI_CARRIER_BRD_H01R1']")
	public WebElement JIRI_CARRIER_BRD_H01R1;
	@FindBy(xpath = "(//select)[6]")
	public WebElement BOM_REV_Number;
	@FindBy(xpath = "(//input[@tooltipevent='focus'])[2]")
	public WebElement FG_Quantity;
	@FindBy(xpath = "(//input[@aria-activedescendant='p-highlighted-option'])[2]")
	public WebElement Allocation_Against_Produ;
	@FindBy(xpath = "(//input[@aria-activedescendant='p-highlighted-option'])[3]")
	public WebElement Allocation_Against;
	@FindBy(xpath = "//input[@class='form-control small-view ng-untouched ng-pristine ng-invalid ng-star-inserted']")
	public WebElement jira_Ref;
	@FindBy(xpath = "(//input[@type='text'])[8]")
	public WebElement jira_Assignee;
	@FindBy(xpath = "//input[@type ='date']")
	public WebElement dateandtime;
	@FindBy(xpath = "//input[@class='form-control small-view ng-dirty ng-touched ng-valid']")
	public WebElement current_date;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement save_Button;
	@FindBy(xpath = "(//select)[5]")
	public WebElement Costcenter;
	@FindBy(xpath = "(//button[@type='button'])[1]")
	public WebElement closebutton;
	
	
	
	@FindBy(xpath = "(//span[text()='Export Data'])[1]")
	public WebElement Export_Data;
	
	
	
	
	@FindBy(xpath = "//span[contains(text(),'BOM')]")
	public WebElement ENGG_BOM;
	//span[contains(text(),'ENGG BOM')]
	
	
				
	
	
	
	
//	  Job Work Allocation
	
	
	
	
//	(//select[@class='form-control small-view ng-untouched ng-pristine ng-valid ng-star-inserted'])[6]
	
	

}
