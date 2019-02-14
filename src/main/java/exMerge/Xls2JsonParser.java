package exMerge;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;

public class Xls2JsonParser extends Excel2JsonParser {
    public Xls2JsonParser(String fname, boolean useFormulaCached) {
        this.fileName = fname;
        this.setUseFormulaCached(useFormulaCached);
    }

    @Override
    public String toJsonString() throws Exception {
        FileInputStream fin = new FileInputStream(this.fileName);
        try (Workbook wb = new HSSFWorkbook(fin)) {
            return calcJsonString(wb);
        }
    }

}
