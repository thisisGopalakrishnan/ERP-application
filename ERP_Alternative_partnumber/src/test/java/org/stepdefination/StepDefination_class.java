 package org.stepdefination;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base_Class;
import base.Excel_data;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.Login_page;

public class StepDefination_class extends Base_Class {
	Login_page login;

	@Given("Login as a QA on ERP page")
	public void login_as_a_qa_on_erp_page() throws IOException {

		launch_browser("edge");
		url("https://qa-erp.e-consystems.net/");
		login = new Login_page();
		login = PageFactory.initElements(getdriver(), Login_page.class);
		login.Username.sendKeys("store@e-consystems.com");
		login.password.sendKeys("Erp@12345");
		login.sign_in_button.click();
	}

	@When("Open issue module and fill the form and export  {string} the excel sheet {string}")
	public void open_issue_module_and_fill_the_form_and_export_the_excel_sheet(String filePath, String sheetName)
			throws Exception {

		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet(sheetName);
		DataFormatter formatter = new DataFormatter();

		String referencenumber = sheet.getRow(1).getCell(2).toString().trim();
		System.out.println("🔍 Reference Number: " + referencenumber);

		Thread.sleep(3000);

		elementwaitvisible(login.issues);
		click(login.issues);
		login.issues.click();

		Select requestType = new Select(login.Fillterby_requesttype);
		requestType.selectByVisibleText("Job Work Allocation");

		login.search_by.sendKeys(referencenumber);
		Thread.sleep(1000);
		login.serach_button.click();
		Thread.sleep(2000);

		if (login.Edit.isDisplayed()) {
			login.Edit.click();
		}
		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) getdriver();
		Actions actions = new Actions(getdriver());
		Robot robot = new Robot();

		// Loop through Excel data
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row currentexcelRow = sheet.getRow(i);
			if (currentexcelRow == null)
				continue;

			String excelPPN = formatter.formatCellValue(currentexcelRow.getCell(0)).trim(); // PPN
			String excelAPN = formatter.formatCellValue(currentexcelRow.getCell(1)).trim(); // Alternate Part

			// Clear and search in PPN search box
			elementwaitclicable(login.search_Box);
			login.search_Box.clear();
			login.search_Box.sendKeys(excelPPN);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			// Get filtered row
			List<WebElement> webRows = getdriver().findElements(By.cssSelector(".slick-row"));
			boolean matched = false;

			for (WebElement row : webRows) {
				List<WebElement> cells = row.findElements(By.cssSelector(".slick-cell"));
				if (cells.size() < 7)
					continue;

				String webPPN = cells.get(4).getText().trim();
				if (!excelPPN.equalsIgnoreCase(webPPN))
					continue;

//		        js.executeScript("arguments[0].scrollIntoView(true);", row);

				// Double-click APN cell
				WebElement apnCell = cells.get(5);
				actions.doubleClick(apnCell).perform();
				Thread.sleep(300);

				// Send APN value
//		        actions.sendKeys(excelAPN).perform();
//		        Thread.sleep(500);

				// Handle dropdown with Robot
//		        robot.keyPress(KeyEvent.VK_DOWN);
//		        robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);

				//code written for my understanding
//				List<WebElement> elements = getdriver().findElements(By.cssSelector(".slick-row"));
//				WebElement updatedrow = elements.get(webRows.indexOf(row));
//				WebElement updatedcell = updatedrow.findElement(By.cssSelector(".slick-cell"));
				
				
				
				// Re-fetch updated cells
				List<WebElement> refreshedRows = getdriver().findElements(By.cssSelector(".slick-row"));
				WebElement updatedRow = refreshedRows.get(webRows.indexOf(row));
				List<WebElement> updatedCells = updatedRow.findElements(By.cssSelector(".slick-cell"));
				
				
				WebElement a = updatedCells.get(5);
				String apnValue = a.getText().toString().trim();
				
				
//				String apnValue = "";
//				try {
//					WebElement apnCellFresh = getdriver().findElements(By.cssSelector(".slick-row"))
//							.get(webRows.indexOf(row)).findElements(By.cssSelector(".slick-cell")).get(5);
//					WebElement input = apnCellFresh.findElement(By.tagName("input"));
//					apnValue = input.getAttribute("value").trim();
//				} catch (Exception e) {
//					WebElement apnCellFresh = getdriver().findElements(By.cssSelector(".slick-row"))
//							.get(webRows.indexOf(row)).findElements(By.cssSelector(".slick-cell")).get(5);
//					apnValue = apnCellFresh.getText().trim();
//				}

//		//         Re-fetch EQ cell separately
				WebElement eqCellFresh = getdriver().findElements(By.cssSelector(".slick-row"))
						.get(webRows.indexOf(row)).findElements(By.cssSelector(".slick-cell")).get(6);
				String eqValue = eqCellFresh.getText().trim();

				String compareValue = apnValue.isEmpty() ? eqValue : apnValue;

				matched = false;
				String[] compareLines = compareValue.split("\\R");
				for (String line : compareLines) {
					if (line.trim().equalsIgnoreCase(excelAPN.trim())) { 
						matched = true;
						break;
					}

				}

				// FOR EQ PART NOT MATCHED CHECK
//		        
//		        if (!matched) {
//		        	WebElement eqCell = cells.get(6);
//		        	actions.doubleClick(eqCell).perform();
//		        	Thread.sleep(2000);
//		        	
////		        	WebElement eqCellUpdated = getdriver()
////			        	    .findElements(By.cssSelector(".slick-row"))
////			        	    .get(webRows.indexOf(row))
////			        	    .findElements(By.cssSelector(".slick-cell")) 
////			        	    .get(6);
////		        	    robot.keyPress(KeyEvent.VK_DOWN);
////				        robot.keyRelease(KeyEvent.VK_DOWN);
////				        robot.keyPress(KeyEvent.VK_ENTER);
////				        robot.keyRelease(KeyEvent.VK_ENTER);
////				        Thread.sleep(1000);
////		        	// Step 2: Get text directly from EQ cell — no <input> expected
//		        	  	
//		        	Thread.sleep(2000);
//		        	String eqText = eqCell.getText().trim();
//		        	System.out.println("🔍 EQ Cell Text  " + eqText);
//
//		        	// Step 3: Split lines and compare
//		        	String[] eqLines = eqText.split("\\R");
//		        	matched = false;
//		        	for (String line : eqLines) {
//		        	    if (line.trim().equalsIgnoreCase(excelAPN.trim())) {
//		        	        matched = true;
//		        	        System.out.println("✅ Match Found in EQ-APN for PPN: " + excelPPN);
//		        	        break;
//		        	    }
//		        	}
//
//		        	if (!matched) {
//		        	    System.out.println("❌ No Match in EQ-APN for PPN: " + excelPPN);
//		        	}
//		        	
//		        }
//
//		        
//		        

				String cleanedCompareValue = compareValue.split("\\R")[0].trim();

				if (matched) {
					System.out.println("✅ Match Found Excel for PPN: " + excelPPN);
					System.out.println("Excel Alt Part  : " + excelAPN);
					System.out.println("Web APN   : " + apnValue);
					System.out.println("Web EQ    : " + eqValue);
					System.out.println("---------------------------------------------------");
					break;
				} else {
					System.out.println("❌ Mismatch Excel for PPN: " + excelPPN);
					System.out.println("Ecel Alt Part  : " + excelAPN);
					System.out.println("Web APN   : " + apnValue);
					System.out.println("Web EQ    : " + eqValue);
					System.out.println("---------------------------------------------------");

				}

			}
		}

	}

	@When("Compare the Excel sheet")
	public void compare_the_excel_sheet() throws IOException {

	}

	@Then("close the browser")
	public void close_the_browser() {

	}

}
