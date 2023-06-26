package excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;
    private static int rowIndex;

    public static void createExcelFile(String filePath) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Sheet1");
        rowIndex = 0;
    }

    public static void createHeaderRow() {
        Row headerRow = sheet.createRow(rowIndex);

        CellStyle dateStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        Cell cell0 = headerRow.createCell(0);
        cell0.setCellValue("#");

        Cell cell1 = headerRow.createCell(1);
        cell1.setCellValue("data");
        cell1.setCellStyle(dateStyle);

        Cell cell2 = headerRow.createCell(2);
        cell2.setCellValue("da registrare");

        Cell cell3 = headerRow.createCell(3);
        cell3.setCellValue("registrate");

        Cell cell4 = headerRow.createCell(4);
        cell4.setCellValue("mancanti");

        Cell cell5 = headerRow.createCell(5);
        cell5.setCellValue("% mancanti");
        
        sheet.setColumnWidth(0, sheet.getColumnWidth(0) * 2);
        sheet.setColumnWidth(1, sheet.getColumnWidth(1) * 2);
        sheet.setColumnWidth(2, sheet.getColumnWidth(2) * 2);
        sheet.setColumnWidth(3, sheet.getColumnWidth(3) * 2);
        sheet.setColumnWidth(4, sheet.getColumnWidth(4) * 2);
        sheet.setColumnWidth(5, sheet.getColumnWidth(5) * 2);

        rowIndex++;
    }

    public static void insertData(int number, String dateString, int toRegister, int registered, int missing, String percentage) {
        Row dataRow = sheet.createRow(rowIndex);

        CellStyle dateStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        Cell cell0 = dataRow.createCell(0);
        cell0.setCellValue(number);

        Cell cell1 = dataRow.createCell(1);
        Date date = parseDateString(dateString);
        cell1.setCellValue(date);
        cell1.setCellStyle(dateStyle);

        Cell cell2 = dataRow.createCell(2);
        cell2.setCellValue(toRegister);

        Cell cell3 = dataRow.createCell(3);
        cell3.setCellValue(registered);

        Cell cell4 = dataRow.createCell(4);
        cell4.setCellValue(missing);

        Cell cell5 = dataRow.createCell(5);
        cell5.setCellValue(parsePercentageString(percentage));
        cell5.setCellStyle(getPercentageCellStyle());
        
     

        rowIndex++;
    }

    private static Date parseDateString(String dateString) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static float parsePercentageString(String percentage) {
        try {
            String cleanPercentage = percentage.replace(",", ".").replace("%", "").trim();
            return Float.parseFloat(cleanPercentage) / 100;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static CellStyle getPercentageCellStyle() {
        CellStyle percentageStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        percentageStyle.setDataFormat(dataFormat.getFormat("0.00%"));
        return percentageStyle;
    }

    public static void saveExcelFile(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("File creato correttamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}