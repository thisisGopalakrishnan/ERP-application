package excel_comparator;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelComparator2 {

    public static void main(String[] args) throws Exception {

        // Load Excel Files
        FileInputStream Source = new FileInputStream("E:\\New folder\\Downloads\\e-CAM0M31_TOF_CUMI3300_MOD_H02R1_Rev E1.0_Assembly_BOM.xlsx");
        FileInputStream ERP = new FileInputStream("E:\\New folder\\Downloads\\JWALC - e-CAM0M31_TOF_CUMI3300_MOD_H02R1 JWAMP2526-0065_7-Jul-2025_12-44-30.xlsx");

        Workbook wb1 = new XSSFWorkbook(Source);
        Workbook wb2 = new XSSFWorkbook(ERP);

        Sheet sheet1 = wb1.getSheet("BoM");
        Sheet sheet2 = wb2.getSheetAt(0);  // Default to first sheet

        DataFormatter formatter = new DataFormatter();

        // Columns: H = 7, O = 14 for File1 (BoM)
        List<String[]> data1 = readData(sheet1, 9, 7, 14, formatter);  // From row 10

        // Columns: C = 2, E = 4 for File2
        List<String[]> data2 = readData(sheet2, 1, 2, 4, formatter);   // From row 2

        // Output workbook
        Workbook resultWb = new XSSFWorkbook();
        Sheet resultSheet = resultWb.createSheet("Comparison");

        // Create Header
        Row header = resultSheet.createRow(0);
        header.createCell(0).setCellValue("MFG Part No (File1)");
        header.createCell(1).setCellValue("QTY (File1)");
        header.createCell(2).setCellValue("Part Name (File2)");
        header.createCell(3).setCellValue("Qty/Unit (File2)");
        header.createCell(4).setCellValue("Match");

        int rowCount = Math.max(data1.size(), data2.size());

        for (int i = 0; i < rowCount; i++) {
            Row row = resultSheet.createRow(i + 1);

            String[] row1 = i < data1.size() ? data1.get(i) : new String[]{"", ""};
            String[] row2 = i < data2.size() ? data2.get(i) : new String[]{"", ""};

            row.createCell(0).setCellValue(row1[0]);
            row.createCell(1).setCellValue(row1[1]);
            row.createCell(2).setCellValue(row2[0]);
            row.createCell(3).setCellValue(row2[1]);

            boolean match = row1[0].equalsIgnoreCase(row2[0]) &&
                            row1[1].equalsIgnoreCase(row2[1]);

            row.createCell(4).setCellValue(match ? "TRUE" : "FALSE");
        }

        FileOutputStream fos = new FileOutputStream("D:\\Screenshots\\ComparisonResult.xlsx");
        resultWb.write(fos);
        fos.close();

        wb1.close();
        wb2.close();
        resultWb.close();

        System.out.println("✅ Comparison complete. Output saved to'ComparisonResult.xlsx'");
    }

    // Generic method to read specific columns
    public static List<String[]> readData(Sheet sheet, int startRow, int col1, int col2, DataFormatter formatter) {
        List<String[]> list = new ArrayList<>();

        for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            String val1 = formatter.formatCellValue(row.getCell(col1)).trim();
            String val2 = formatter.formatCellValue(row.getCell(col2)).trim();

            if (!val1.isEmpty() || !val2.isEmpty()) {
                list.add(new String[]{val1, val2});
            }
        }

        return list;
    }
}
