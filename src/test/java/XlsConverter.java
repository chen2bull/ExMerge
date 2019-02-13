import exMerge.Json2XlsParser;
import exMerge.Xls2JsonParser;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
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
        System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.CommonsLogger" );
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String baseName =  file.getName();
                if (!baseName.endsWith(".xls")) {
                    continue;
                }
//                String outputName = pathName + baseName.replace(".xls", ".xlsx");
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
//                FileInputStream fin = new FileInputStream(outputName);
//                HSSFWorkbook wb1 = new HSSFWorkbook(fin);
//                FormulaEvaluator evaluator = wb1.getCreationHelper().createFormulaEvaluator();
//                evaluator.evaluateAll();
//                fin.close();
//                FileOutputStream fout2 = new FileOutputStream(outputName);
//                wb1.write(fout2);
//                wb1.close();
            }
        }
    }
}
