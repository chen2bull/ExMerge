package exMerge;

import com.fasterxml.jackson.core.type.TypeReference;
import exMerge.bean.CellBean;
import exMerge.bean.SheetBean;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Converter {
    private static String calc_sheet_text(Sheet sheet) throws Exception {
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

    public static String workbook2JsonString(String fname) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (Workbook wb = open_workbook(fname)) {
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
                sb.append(calc_sheet_text(sheet));
                sb.append("]}");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    public static HSSFWorkbook jsonFile2HSSFWorkBook(String string) throws Exception {
        Path path = FileSystems.getDefault().getPath(string);
        String jsonText = new String(Files.readAllBytes(path));
        return jsonText2HSSFWorkBook(jsonText);
    }

    private static Workbook open_workbook(String fname) throws Exception {
        FileInputStream fin = new FileInputStream(fname);
        if (fname.endsWith(".xlsx")) {
            return new XSSFWorkbook(fin);
        } else if (fname.endsWith("*.xls")) {
            return new HSSFWorkbook(fin);
        }
        throw new Exception("File:" + fname + " is not a Excel file.");
    }

    public static HSSFWorkbook jsonText2HSSFWorkBook(String jsonText) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        ObjectMapper mapper = new ObjectMapper();
//         List<exMerge.bean.SheetBean> sheetBeans = mapper.readValue(jsonText, List.class); // 这种用法是错的
        ArrayList<SheetBean> sheetBeans = mapper.readValue(jsonText, new TypeReference<ArrayList<SheetBean>>(){});
        System.out.println(sheetBeans);
        System.out.println(sheetBeans.getClass());
        System.out.println(sheetBeans.get(0).getClass());
        for (SheetBean sheetB : sheetBeans) {
            Sheet s = wb.createSheet(sheetB.getSheetName());
            ArrayList<ArrayList<CellBean>> content = sheetB.getContent();
            int rowLength = content.size();
            for (int i = 0; i < rowLength; i++) {
                List<CellBean> rowBean = content.get(i);
                Row row = s.createRow(i);
                int colLength = rowBean.size();
                for (int j = 0; j < colLength; j++) {
                    Cell cell = row.createCell(j);
                    CellBean cellBean = rowBean.get(j);
                    cell.setCellType(cellBean.getT());
                }
            }

        }
        return wb;
    }
}
