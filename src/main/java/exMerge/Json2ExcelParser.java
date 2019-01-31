package exMerge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exMerge.bean.*;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;

abstract class Json2ExcelParser {
    String jsonText;

    private void setCellByBean(Workbook wb, Cell cell, CellBean cellBean, Drawing drawing) {
        cell.setCellType(cellBean.getT());
        cell.setCellValue(cellBean.getV());
        StyleBean styleBean = cellBean.getS();
        if (styleBean != null) {
            if (!styleBean.isDefaultStyle()) {
                cell.setCellStyle(styleBean.createStyle(wb));
            }
        }
        CommentBean commentBean = cellBean.getC();
        if (commentBean != null) {
            cell.setCellComment(commentBean.createComment(wb, drawing));
        }
    }

    void addInfoFromJson(Workbook wb) throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<SheetBean> sheetBeans = mapper.readValue(jsonText, new TypeReference<ArrayList<SheetBean>>() {
        });
        for (SheetBean sheetB : sheetBeans) {
            Sheet s = wb.createSheet(sheetB.getSheetName());
            Drawing drawing = s.createDrawingPatriarch();
            ArrayList<ArrayList<CellBean>> content = sheetB.getContent();
            SheetMetaBean metaBean = sheetB.getMeta();
            ArrayList<Short> heights = metaBean.getHeights();
            int heightsLen = heights.size();
            ArrayList<Integer> widths = metaBean.getWidths();
            for (int i = 0; i < widths.size(); i++) {
                s.setColumnWidth(i, widths.get(i));
            }
            for (int i = 0; i < content.size(); i++) {
                List<CellBean> rowBean = content.get(i);
                Row row = s.createRow(i);
                if(i < heightsLen) {
                    row.setHeight(heights.get(i));
                }
                int colLength = rowBean.size();
                for (int j = 0; j < colLength; j++) {
                    Cell cell = row.createCell(j);
                    CellBean cellBean = rowBean.get(j);
                    setCellByBean(wb, cell, cellBean, drawing);
                }
            }

        }
    }
}
