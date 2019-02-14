package exMerge;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;

public class Json2XlsxParser extends Json2ExcelParser {
    public Json2XlsxParser(String jsonText) {
        this.jsonText = jsonText;
    }

    public Workbook toExcel() throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        this.wb = wb;
        this.evaluator = wb.getCreationHelper().createFormulaEvaluator();
        this.evalCells = new ArrayList<>();
        addInfoFromJson();
        for (int i = 0; i < 3; i++) {
            for (Cell cell : this.evalCells) {
                this.evaluator.evaluateFormulaCell(cell);
            }
        }
//        XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
        return wb;
    }
}
