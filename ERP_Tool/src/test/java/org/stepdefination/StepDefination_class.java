package org.stepdefination;

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
import org.openqa.selenium.WebElement;
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
		login.Username.sendKeys("PM@e-consystems.com");
		login.password.sendKeys("Erp@12345");
		login.sign_in_button.click();
	}

	@When("Open issue module and fill the form and export  {string} the excel sheet {string}")
	public void open_issue_module_and_fill_the_form_and_export_the_excel_sheet(String filePath, String BoM)
			throws InterruptedException, IOException {
		Object[][] testData = Excel_data.data_from_excel(filePath, BoM);
		String Request_type = testData[2][4].toString(); // E4 = row 3 (index 2), column E (index 4)
		String dropdownValueFromExcel = testData[3][4].toString();
		System.out.println("excel dropdown value for rev "+dropdownValueFromExcel);
		System.out.println("Value at E4: " + Request_type);
		elementwaitvisible(login.issues);
		login.issues.click();

		elementwaitvisible(login.addbutton);
		login.addbutton.click();

		elementwaitvisible(login.RequestType);
		login.RequestType.click();
		Select select = new Select(login.RequestType);
		select.selectByIndex(5);
		Thread.sleep(3000);
		Select select2 = new Select(login.Costcenter);
		select2.selectByIndex(5);
		Thread.sleep(3000);
		elementwaitvisible(login.ProductCode);
		login.ProductCode.sendKeys(Request_type);
		Thread.sleep(3000);
		login.ProductCode.click();
//	Thread.sleep(3000);
//	login.JIRI_CARRIER_BRD_H01R1.click();
		login.Bomtype.click();
		login.SVN_ENG_Close.click();
		Thread.sleep(2000);
		login.Bomtype.click();
		Thread.sleep(2000);
//	elementwaitclicable(login.SVN_ENG_BOM);
		login.Automataion.click();
		// login.SVN_ENG_BOM.click();
		login.SVN_ENG_Close.click();
		Thread.sleep(3000);
		elementwaitvisible(login.BOM_REV_Number);
//		WebElement element = getdriver().findElement(By.xpath("//option[text()='X1.1']"));
//		element.click();
		login.BOM_REV_Number.click();
		
		Select Bomrev = new Select(login.BOM_REV_Number);
		Bomrev.selectByContainsVisibleText(dropdownValueFromExcel);
		Thread.sleep(3000);
		login.FG_Quantity.sendKeys("7");

		Thread.sleep(2000);
		login.Allocation_Against_Produ.sendKeys("DENEBOLA_CX3_RDK");
		Thread.sleep(3000);
		login.Allocation_Against_Produ.click();
		Thread.sleep(3000);
		login.Allocation_Against.sendKeys("Aakashshah");
		login.Allocation_Against.click();
		login.jira_Ref.sendKeys("12335");
		String timestamp = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		login.dateandtime.sendKeys(timestamp);
		Thread.sleep(1000);
		login.jira_Assignee.sendKeys("Kishore");
		Thread.sleep(1000);
		((JavascriptExecutor) getdriver()).executeScript("window.scrollBy(0, 4000);");
//	scroll();
		Thread.sleep(1000);
		elementwaitclicable(login.save_Button);
		click(login.save_Button);
		login.save_Button.click();
		Thread.sleep(7000);
//	login.Fillterby_requesttype.click();
		elementwaitclicable(login.Fillterby_requesttype);
		Select select3 = new Select(login.Fillterby_requesttype);
		select3.selectByVisibleText("Job Work Allocation");
		Thread.sleep(7000);
//	login.Fillterby_requesttype.click();
		elementwaitclicable(login.Edit);
		login.Edit.click();
		elementwaitclicable(login.Export_Data);
		login.Export_Data.click();
		Thread.sleep(2000);
		getdriver().close();

//	((JavascriptExecutor) getdriver()).executeScript("window.scrollTo(0, 0);");

//	JavascriptExecutor js = (JavascriptExecutor) getdriver();
//
//	// Scroll to top
//	js.executeScript("window.scrollTo(0, 0);");
//	Thread.sleep(2000); // allow time for page to settle
//
//	// Try clicking again
//	WebElement closeBtn = login.closebutton;
//
//	// Scroll element into view — better for edge cases
//	js.executeScript("arguments[0].scrollIntoView({block: 'center'});", closeBtn);
//
//	WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(10));
//	wait.until(ExpectedConditions.elementToBeClickable(closeBtn));
//
//	closeBtn.click();
//	
	}

	@When("Compare the Excel sheet")
	public void compare_the_excel_sheet() throws IOException {
		// Load Excel Files
//        FileInputStream fis1 = new FileInputStream("E:\\New folder\\Downloads\\e-CAM85_CUMI0830C_MOD_H08R1_Rev E1.1_Assembly_BOM.xlsx");
//        FileInputStream fis2 = new FileInputStream("E:\\New folder\\Downloads\\e-CAM85_CUMI0830C_MOD_H08R1.xlsx");
//
//        Workbook wb1 = new XSSFWorkbook(fis1);
//        Workbook wb2 = new XSSFWorkbook(fis2);
//
//        Sheet sheet1 = wb1.getSheet("BoM");
//        Sheet sheet2 = wb2.getSheetAt(0);  // Default to first sheet
//
//        DataFormatter formatter = new DataFormatter();
//
//        // Columns: H = 7, O = 14 for File1 (BoM)
//        List<String[]> data1 = readData(sheet1, 9, 7, 14, formatter);  // From row 10
//
//        // Columns: C = 2, E = 4 for File2
//        List<String[]> data2 = readData(sheet2, 1, 2, 4, formatter);   // From row 2
//
//        // Output workbook
//        Workbook resultWb = new XSSFWorkbook();
//        Sheet resultSheet = resultWb.createSheet("Comparison");
//
//        // Create Header
//        Row header = resultSheet.createRow(0);
//        header.createCell(0).setCellValue("MFG Part No (File1)");
//        header.createCell(1).setCellValue("QTY (File1)");
//        header.createCell(2).setCellValue("Part Name (File2)");
//        header.createCell(3).setCellValue("Qty/Unit (File2)");
//        header.createCell(4).setCellValue("Match");
//
//        int rowCount = Math.max(data1.size(), data2.size());
//
//        for (int i = 0; i < rowCount; i++) {
//            Row row = resultSheet.createRow(i + 1);
//
//            String[] row1 = i < data1.size() ? data1.get(i) : new String[]{"", ""};
//            String[] row2 = i < data2.size() ? data2.get(i) : new String[]{"", ""};
//
//            row.createCell(0).setCellValue(row1[0]);
//            row.createCell(1).setCellValue(row1[1]);
//            row.createCell(2).setCellValue(row2[0]);
//            row.createCell(3).setCellValue(row2[1]);
//
//            boolean match = row1[0].equalsIgnoreCase(row2[0]) &&
//                            row1[1].equalsIgnoreCase(row2[1]);
//
//            row.createCell(4).setCellValue(match ? "TRUE" : "FALSE");
//        }
//
//        FileOutputStream fos = new FileOutputStream("D:\\Screenshots\\ComparisonResult.xlsx");
//        resultWb.write(fos);
//        fos.close();
//
//        wb1.close();
//        wb2.close();
//        resultWb.close();
//
//        System.out.println("✅ Comparison complete. Output saved to'ComparisonResult.xlsx'");
//		return null;
	}

	// Generic method to read specific columns
	public static List<String[]> readData(Sheet sheet, int startRow, int col1, int col2, DataFormatter formatter) {
		List<String[]> list = new ArrayList<>();

		for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null)
				continue;

			String val1 = formatter.formatCellValue(row.getCell(col1)).trim();
			String val2 = formatter.formatCellValue(row.getCell(col2)).trim();

			if (!val1.isEmpty() || !val2.isEmpty()) {
				list.add(new String[] { val1, val2 });
			}
		}

		return list;
	}

	@Then("close the browser")
	public void close_the_browser() {

	}

}
