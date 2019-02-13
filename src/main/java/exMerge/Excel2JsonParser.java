package exMerge;

import com.fasterxml.jackson.databind.ObjectMapper;
import exMerge.bean.CellBean;
import exMerge.bean.SheetMetaBean;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;

abstract class Excel2JsonParser {
    private String calcSheetText(Sheet sheet) throws Exception {
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        DataFormatter formatter = new DataFormatter();
        boolean isFirstRow = true;
        for (Row row: sheet) {
            if (!isFirstRow) {
                sb.append(",");
            }
            isFirstRow = false;
            if(row == null) {
                sb.append("[]\n");
                continue;
            }
            sb.append("[\n");
            boolean isFirstCell = true;
            ArrayList<CellBean> cellBeans = new ArrayList<>();
            int colNum = row.getLastCellNum();
            for (int c = 0; c < colNum; c++) {
                Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                CellBean cellBean = new CellBean(cell, formatter, this.useFormulaCached);
                cellBeans.add(cellBean);
            }
            if (isEmptyLine(cellBeans)) {
                sb.append("]\n");
                break;
            }
            for (CellBean cb: cellBeans) {
                if (!isFirstCell) {
                    sb.append(",\n");
                }
                isFirstCell = false;
                sb.append(mapper.writeValueAsString(cb));
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    private static boolean isEmptyLine(ArrayList<CellBean> cellBeans) {
        if (cellBeans.size() == 0) {
            return true;
        }
        for (CellBean cb: cellBeans) {
            if (cb.getT() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    String fileName;
    private boolean useFormulaCached;

    public void setUseFormulaCached(boolean useFormulaCached) {
        this.useFormulaCached = useFormulaCached;
    }

    public abstract String toJsonString() throws Exception;

    String calcJsonString(Workbook wb) throws Exception {
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
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
            SheetMetaBean sheetMetaBean = new SheetMetaBean(sheet);
            sb.append(",\n\"meta\":");
            sb.append(mapper.writeValueAsString(sheetMetaBean));
            sb.append(",\n\"content\":[");
            sb.append(calcSheetText(sheet));
            sb.append("]}");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
