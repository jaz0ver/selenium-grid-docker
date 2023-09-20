package framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelReader{
    
    private Logger Log = LoggerFactory.getLogger(ExcelReader.class);
    private Sheet excelSheet;
    private String excelName, sheetName;

    public ExcelReader(String excelName, String sheetName) {
        setExcelXlsx(excelName, sheetName);
    }

    public ExcelReader() {
    }

    public void setExcelXlsx(String excelName, String sheetName) {

        /********************
         * Excel file setup
         *******************/

        this.sheetName = sheetName; 
        if (this.excelName != excelName || excelSheet == null) {
            File excelFile = new File(
                    System.getProperty("user.dir") + "\\src\\main\\resources\\testdata\\" + excelName + ".xlsx");
            try {
                FileInputStream excelStream = new FileInputStream(excelFile);
                Workbook excelWorkbook = WorkbookFactory.create(excelStream);
                excelSheet = excelWorkbook.getSheet(sheetName);
            } catch (FileNotFoundException e) {
                Log.error(excelName + ".xlsx was not found.");
                e.printStackTrace();
            } catch (EncryptedDocumentException | IOException e) {
                e.printStackTrace();
            }
        }
        this.excelName = excelName;
    }

    public void checkExcel() {
        throwNullPointerException(excelName == null, "Ensure setExcelXlsx method is configured");
        throwNullPointerException(excelSheet == null, "Please specify the correct sheet name. "+sheetName + " sheet was not found in " + excelName + ".xlsx");
    }

    private int getTotalRow(int adjustRow) {
        int totalRows = excelSheet.getLastRowNum() + adjustRow;
        // Log.info("Total row: " + totalRows);
        return totalRows;
    }

    private int getTotalColumn() {
        Row rowCells = excelSheet.getRow(0);
        int totalColumn = rowCells.getLastCellNum();
        // Log.info("Total column: " + totalColumn);
        return totalColumn;
    }

    public String[][] getAllData(String excelName, String sheetname) {

        /*****************************************
         * Extract all test data in a sheet
         * Note: Columns must be specified exactly
         *****************************************/

        setExcelXlsx(excelName, sheetname);
        checkExcel();
        int totalRows = getTotalRow(0);
        int totalColumn = getTotalColumn();

        DataFormatter format = new DataFormatter();
        String testdata[][] = new String[totalRows][totalColumn];
        for (int r = 0; r < totalRows; r++) {
            for (int c = 0; c < totalColumn; c++) {
                testdata[r][c] = format.formatCellValue(excelSheet.getRow(r + 1).getCell(c));
                // Log.info("Testdata: " + "[" + (r) + "]" + "[" + c + "]: " + testdata[r][c]);
            }
        }
        return testdata;
    }

    public String getData(int row, int column) {

        /***************************************************
         * Extract data in reference to row and column count
         ***************************************************/
        
        checkExcel();
        int totalRows = getTotalRow(1);
        throwArrayIndexOutOfBoundsException(row > totalRows, "Specified row was greater than the total row of " + excelName + ".xlsx");

        int totalColumn = getTotalColumn();
        throwArrayIndexOutOfBoundsException(column > totalColumn, "Specified column was greater than the total column of " + excelName + ".xlsx");

        DataFormatter format = new DataFormatter();
        String testdata = format.formatCellValue(excelSheet.getRow(row).getCell(column-1));
        return testdata;
    }

    public String getData(String rowName, String columnName) {

        /**************************************************
         * Extract data in reference to row and column name
         **************************************************/
            
            checkExcel();
            DataFormatter format = new DataFormatter();
            String testdata = format.formatCellValue(excelSheet.getRow(getRowCount(rowName)).getCell(getColumnCount(columnName)));
            return testdata;
    }

    public void getAllHeader() {
        checkExcel();

        int totalColumn = getTotalColumn();
        DataFormatter format = new DataFormatter();
        String testdata[] = new String[totalColumn];
        for (int c = 0; c < totalColumn; c++) {
            testdata[c] = (format.formatCellValue(excelSheet.getRow(0).getCell(c))).trim();
            Log.info("Test data[" + c + "]: " + testdata[c]); 
        }
    }

    public int getColumnCount(String columnName) {
        checkExcel();

        int totalColumn = getTotalColumn();
        int columnCount = 0;

        DataFormatter format = new DataFormatter();
        String testdata[] = new String[totalColumn];
        for (int c = 0; c < totalColumn; c++) {
            testdata[c] = (format.formatCellValue(excelSheet.getRow(0).getCell(c))).trim();
            // Log.info("Test data[" + c + "]: " + testdata[c]);
            if (testdata[c].equals(columnName.trim())) {
                columnCount = c;
                break;
            }
        }
        throwNullPointerException(columnCount == 0, "\"" + columnName + "\" was not found. Excel file: " + excelName + ". Sheet name: " + sheetName);
        return columnCount;
    }

    public int getRowCount(String rowName) {
        checkExcel();

        int totalRow = getTotalRow(1);
        int rowCount = 0;

        DataFormatter format = new DataFormatter();
        String testdata[] = new String[totalRow];
        for (int r = 0; r < totalRow; r++) {
            testdata[r] = (format.formatCellValue(excelSheet.getRow(r).getCell(0))).trim();
            // Log.info("Test data[" + r + "]: " + testdata[r]);
            if (testdata[r].equals(rowName.trim())) {
                rowCount = r;
                break;
            }
        }
        throwNullPointerException(rowCount == 0, "\"" + rowName + "\" was not found. Excel file: " + excelName + ". Sheet name: " + sheetName);
        return rowCount;
    }
    
    public void throwNullPointerException(Boolean condition, String message) {
        if (condition) {
            Log.error(message);
            throw new NullPointerException(message);
        }
    }

    public void throwArrayIndexOutOfBoundsException(Boolean condition, String message) {
        if (condition) {
            Log.error(message);
            throw new NullPointerException(message);
        }
    }

}
