import exMerge.Json2XlsParser;
import exMerge.Xlsx2JsonParser;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConverterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void toWorkbook() throws Exception {
        Path path = Paths.get("src",  "test",  "resources", "z组合boss.xlsx");
        Xlsx2JsonParser converter = new Xlsx2JsonParser(path.toString(), true);
        String text = converter.toJsonString();
        System.out.println(text);
        Json2XlsParser jParser = new Json2XlsParser(text);
        Workbook wb = jParser.toExcel();
        FileOutputStream fout = new FileOutputStream("temp" + File.separator + "new组合.xls");
        wb.write(fout);
        wb.close();
        fout.close();
    }
}