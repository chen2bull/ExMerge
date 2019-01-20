import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;

public class ConverterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void toWorkbook() throws Exception {
        String text = Converter.workbook2JsonString("src\\main\\resources\\z组合boss.xlsx");
        Workbook wb = Converter.jsonText2HSSFWorkBook(text);
        FileOutputStream fout = new FileOutputStream("组合.xls");
        wb.write(fout);
    }
}