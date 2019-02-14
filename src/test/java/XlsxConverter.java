import exMerge.parser.Json2XlsxParser;
import exMerge.parser.Xls2JsonParser;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class XlsxConverter {


    @Test
    public void testAllExcels() throws Exception {
        String pathName = "temp" + File.separator;
        File outPath = new File(pathName);
        outPath.mkdir();
        String[] pathStrings = {"src", "test", "resources"};
        File folder = new File(String.join(File.separator, pathStrings));
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
                String outputName = pathName + baseName.replace(".xls", ".xlsx");
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
                Json2XlsxParser jParser = new Json2XlsxParser(text);
                Workbook wb = jParser.toExcel();
                FileOutputStream fout = new FileOutputStream(outputName);
                wb.write(fout);
                fout.close();
                wb.close();
            }
        }
    }
}
