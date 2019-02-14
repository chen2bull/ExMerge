import exMerge.parser.Json2XlsParser;
import exMerge.parser.Xls2JsonParser;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class XlsConverter {


    @Test
    public void testAllExcels() throws Exception {
        String pathName = "temp" + File.separator;
        File outPath = new File(pathName);
        outPath.mkdir();
        String[] pathStrings = {"src", "test", "resources"};
        System.out.println(String.join(File.separator, pathStrings));
        File folder = new File(String.join(File.separator, pathStrings));
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            throw new Exception("not working!");
        }
//      System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.CommonsLogger" );
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String baseName =  file.getName();
                if (!baseName.endsWith(".xls")) {
                    continue;
                }
                String outputName = pathName + baseName;
                String fileName = file.getAbsoluteFile().toString();
                System.out.println(fileName);
                Xls2JsonParser parser = new Xls2JsonParser(fileName, true);
                String text = parser.toJsonString();
                // 先写文本文件
                String textFile = pathName + baseName.replace(".xls", ".json");
                FileWriter fileWriter = new FileWriter(textFile);
                fileWriter.write(text);
                fileWriter.close();

                // 生成excel
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
