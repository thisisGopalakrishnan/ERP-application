package base;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class Excel_data {
	 public static Object[][] data_from_excel(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(1).getLastCellNum();

        Object[][] data = new Object[rowCount][columnCount];
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                data[i - 1][j] = formatter.formatCellValue(row.getCell(j));
            }
        }

        workbook.close();
        return data;
    }

}
