package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelUtils {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    public List<HashMap<String, String>> readExcel(String path, String sheetName) throws IOException {
        var fis = new FileInputStream(new File(path));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);
        List<HashMap<String, String>> data = new ArrayList<>();
        int rowNo = 0;
        List<String> headers = new ArrayList<>();
        var itr = sheet.rowIterator();
        while (itr.hasNext()) {
            rowNo++;
            var row = itr.next();
            var cellItr = row.cellIterator();
            HashMap<String, String> rowValues = new HashMap<>();
            int colNo = 0;
            while (cellItr.hasNext()) {
                colNo++;
                Cell cell = cellItr.next();
                addToCell(rowValues, headers, cell, rowNo, colNo);
            }
            if (rowNo != 1)
                data.add(rowValues);
        }
        return data;
    }

    void addToCell(HashMap<String, String> rowValues, List<String> headers, Cell cell, int rowNo, int colNo) {
        if (rowNo == 1) {
            headers.add(getCellValue(cell));
        } else {
            rowValues.put(headers.get(colNo - 1), getCellValue(cell));
        }

    }

    String getCellValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC)
            return String.valueOf(cell.getNumericCellValue());
        else if (cell.getCellType() == CellType.STRING)
            return cell.getStringCellValue();
        else
            return "";
    }
}
