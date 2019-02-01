import exMerge.Json2XlsParser;
import exMerge.Xls2JsonParser;
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
                if (!baseName.endsWith(".xls")) {
                    continue;
                }
                String outputName = pathName + baseName;
                String fileName = file.getAbsoluteFile().toString();
                System.out.println(fileName);
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
    }
}
