package exMerge;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Json2XlsxParser extends Json2ExcelParser {
    public Json2XlsxParser(String jsonText) {
        this.jsonText = jsonText;
    }

    public Workbook toExcel() throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        this.wb = wb;
        this.evaluator = new XSSFFormulaEvaluator(wb);
        addInfoFromJson();
        return wb;
    }
}
