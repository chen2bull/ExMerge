import exMerge.Json2XlsParser;
import exMerge.Xls2JsonParser;
import exMerge.Xlsx2JsonParser;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

public class XlsConverter {


    @Test
    public void testAllExcels() throws Exception {
        String pathName = "/media/sf_VirtualBoxShare/whatever/";
        File folder = new File("/home/cmj/ttlz2/common/design/");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            throw new Exception("not working!");
        }
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String baseName =  file.getName();
                String outputName = pathName + baseName;
                String fileName = file.getAbsoluteFile().toString();
                Xls2JsonParser parser = new Xls2JsonParser(fileName);
                String text = parser.toJsonString();
                Json2XlsParser jParser = new Json2XlsParser(text);
                Workbook wb = jParser.toExcel();
                FileOutputStream fout = new FileOutputStream(outputName);
                wb.write(fout);
                fout.close();
                wb.close();

            }
        }
//        Xlsx2JsonParser converter = new Xlsx2JsonParser();
//        String text = converter.toJsonString();
//        System.out.println(text);
//        Json2XlsParser jParser = new Json2XlsParser(text);
//        Workbook wb = jParser.toExcel();
//        FileOutputStream fout = new FileOutputStream("new组合.xls");
//        wb.write(fout);
//        wb.close();
//        fout.close();
    }
}
