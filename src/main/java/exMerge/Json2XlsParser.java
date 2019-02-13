package exMerge;

//import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;


public class Json2XlsParser extends Json2ExcelParser {

    public Json2XlsParser(String jsonText) {
        this.jsonText = jsonText;
    }

    public Workbook toExcel() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        this.wb = wb;
        this.evaluator = wb.getCreationHelper().createFormulaEvaluator();
        this.evalCells = new ArrayList<>();
        addInfoFromJson();
        for (int i = 0; i < 3; i++) { // 某些formula用到的cell是一个formula，且被用到的cell在后面
            for (Cell cell : this.evalCells) {
                this.evaluator.evaluateFormulaCell(cell);
            }
        }
//        HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
        return wb;
    }

}
