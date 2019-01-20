import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    static String calc_sheet_text(Sheet sheet) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        boolean isFirstRow = true;
        for(Row row: sheet) {
            if(!isFirstRow) {
                sb.append(",");
            }
            isFirstRow = false;
            sb.append("[\n");
            boolean isFirstCell = true;
            for(Cell cell: row) {
                if(!isFirstCell) {
                    sb.append(",\n");
                }
                isFirstCell = false;
                CellType cellType = cell.getCellType();
                String cellString = cell.toString();
                Comment comment = cell.getCellComment();
                CellStyle cellStyle = cell.getCellStyle();
                CellBean cellBean = new CellBean(cellType, cellString, comment, cellStyle);
                sb.append(mapper.writeValueAsString(cellBean));
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
