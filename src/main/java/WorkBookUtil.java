import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

class WorkBookUtil {
    static Workbook open_workbook(String fname) throws Exception {
        FileInputStream fin = new FileInputStream(fname);
        if (fname.endsWith(".xlsx")) {
            return new XSSFWorkbook(fin);
        } else if (fname.endsWith("*.xls")) {
            return new HSSFWorkbook(fin);
        }
        throw new Exception("File:" + fname + " is not a Excel file.");
    }

    static void close_workbook(Workbook wb) throws IOException {
        wb.close();
    }

}
