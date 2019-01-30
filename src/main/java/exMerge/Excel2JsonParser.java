package exMerge;

import com.fasterxml.jackson.databind.ObjectMapper;
import exMerge.bean.CellBean;
import org.apache.poi.ss.usermodel.*;

abstract class Excel2JsonParser {
    private static String calcSheetText(Sheet sheet) throws Exception {
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        boolean isFirstRow = true;
        for (Row row : sheet) {
            if (!isFirstRow) {
                sb.append(",");
            }
            isFirstRow = false;
            sb.append("[\n");
            boolean isFirstCell = true;
            for (Cell cell : row) {
                if (!isFirstCell) {
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
    String fileName;

    public abstract String toJsonString() throws Exception;

    String calcJsonString(Workbook wb) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        boolean isFirstSheet = true;
        for (Sheet sheet : wb) {
            if (!isFirstSheet) {
                sb.append(",\n");
            }
            isFirstSheet = false;
            sb.append("{ \"sheetName\":");
            sb.append("\"");
            sb.append(sheet.getSheetName().replace("\"", "\\\""));
            sb.append("\"");
            sb.append(",\"content\":[");
            sb.append(calcSheetText(sheet));
            sb.append("]}");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
