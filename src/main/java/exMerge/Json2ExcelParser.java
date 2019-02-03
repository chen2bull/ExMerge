package exMerge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import exMerge.bean.*;
import org.apache.poi.ss.usermodel.*;
import static org.apache.poi.ss.usermodel.CellType.*;

import java.util.ArrayList;
import java.util.List;


abstract class Json2ExcelParser {
    String jsonText;

    private void setCellByBean(Workbook wb, StylePool stylePool, Cell cell, CellBean cellBean, Drawing drawing) throws JsonProcessingException {
        CellType cellType = cellBean.getT();
        cell.setCellType(cellType);
        switch (cellType) {
            case STRING:
                cell.setCellValue(cellBean.getV());
                break;
            case NUMERIC:
                try{
                    cell.setCellValue(Integer.parseInt(cellBean.getV()));
                }catch(NumberFormatException e){
                    //not int
                }
                try{
                    cell.setCellValue(Float.parseFloat(cellBean.getV()));
                }catch(NumberFormatException e){
                    //not float
                }
                cell.setCellValue(Boolean.valueOf(cellBean.getV()));
                break;
            case BOOLEAN:
                cell.setCellValue(Boolean.valueOf(cellBean.getV()));
                break;
            case FORMULA:
                cell.setCellValue(cellBean.getV());
                break;
            case BLANK:
                cell.setCellValue(cellBean.getV());
                break;
            default:
                cell.setCellValue(cellBean.getV());
        }
        cell.setCellValue(cellBean.getV());
        StyleBean styleBean = cellBean.getS();
        if (styleBean != null) {
            if (!StyleBean.isDefaultStyle(styleBean)) {
                String styleString = stylePool.calcStyleString(styleBean);
                if (stylePool.isExist(styleString)) {
                    cell.setCellStyle(stylePool.getStyle(styleString));
                } else {
                    CellStyle style = styleBean.createStyle(wb);
                    stylePool.updateStyle(styleString, style);
                    cell.setCellStyle(style);
                }
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
            StylePool stylePool = new StylePool();
            ArrayList<ArrayList<CellBean>> content = sheetB.getContent();
            SheetMetaBean metaBean = sheetB.getMeta();
            ArrayList<Short> heights = metaBean.getHeights();
            ArrayList<Integer> widths = metaBean.getWidths();
            for (int i = 0; i < widths.size(); i++) {
                s.setColumnWidth(i, widths.get(i));
            }
            for (int i = 0; i < content.size(); i++) {
                List<CellBean> rowBean = content.get(i);
                Row row = s.createRow(i);
                if (i < heights.size()) {
                    row.setHeight(heights.get(i));
                }
                int colLength = rowBean.size();
                for (int j = 0; j < colLength; j++) {
                    Cell cell = row.createCell(j);
                    CellBean cellBean = rowBean.get(j);
                    setCellByBean(wb, stylePool, cell, cellBean, drawing);
                }
            }

        }
    }
}
