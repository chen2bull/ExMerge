package exMerge;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


public class Json2XlsParser extends Json2ExcelParser {

    public Json2XlsParser(String jsonText) {
        this.jsonText = jsonText;
    }

    public Workbook toExcel() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        this.wb = wb;
        this.evaluator = wb.getCreationHelper().createFormulaEvaluator();
        addInfoFromJson();
        this.evaluator.evaluateAll();
        return wb;
    }

}
