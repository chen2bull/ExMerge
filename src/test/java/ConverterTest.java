import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        String text = Converter.workbook2JsonString(path.toString());
        System.out.println(text);
//        Workbook wb = Converter.jsonText2HSSFWorkBook(text);
//        FileOutputStream fout = new FileOutputStream("组合.xls");
//        wb.write(fout);
    }
}