package exMerge.parser;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class Xlsx2JsonParser extends Excel2JsonParser {
    public Xlsx2JsonParser(String fname, boolean useFormulaCached) {
        this.fileName = fname;
        this.setUseFormulaCached(useFormulaCached);
    }

    @Override
    public String toJsonString() throws Exception {
        FileInputStream fin = new FileInputStream(this.fileName);
        try (Workbook wb = new XSSFWorkbook(fin)) {
            fin.close();
            wb.close();
            return calcJsonString(wb);
        }
    }
}
