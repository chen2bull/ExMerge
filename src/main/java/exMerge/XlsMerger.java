package exMerge;

import exMerge.parser.Json2XlsParser;
import exMerge.parser.Xls2JsonParser;
import name.fraser.neil.plaintext.diff_match_patch;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.LinkedList;

public class XlsMerger {
    public static void main(String[] args) throws Exception {
        diff_match_patch dmp = new diff_match_patch();
        if (args.length != 4) {
            throw new Exception("wrong args length");
        }
        String base = args[0];
        String theirs = args[1];
        String mine = args[2];
        String merged = args[3];
        String baseText = new Xls2JsonParser(base, true).toJsonString();
        String theirsText = new Xls2JsonParser(theirs, true).toJsonString();
        String mineText = new Xls2JsonParser(mine, true).toJsonString();

        LinkedList<diff_match_patch.Patch> minePatchs = dmp.patch_make(baseText, mineText);
        Object[] results = dmp.patch_apply(minePatchs, theirsText);
        String resultText = (String) results[0];

        try{
            Json2XlsParser jParser = new Json2XlsParser(resultText);
            Workbook wb = jParser.toExcel();
            FileOutputStream fout = new FileOutputStream(merged);
            wb.write(fout);
            fout.close();
            wb.close();
        } catch (Exception e) {
            throw new Exception("自动合并失败,请手动合并", e);
        }
    }
}
