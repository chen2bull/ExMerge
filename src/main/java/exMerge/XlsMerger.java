package exMerge;

import exMerge.parser.Json2XlsParser;
import exMerge.parser.Xls2JsonParser;
import name.fraser.neil.plaintext.diff_match_patch;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
        try {
            dmp.Match_Distance = 100000; // 没有博士学位的话很难看懂的
            LinkedList<diff_match_patch.Patch> minePatches = dmp.patch_make(baseText, mineText);
            LinkedList<diff_match_patch.Patch> theirsPatches = dmp.patch_make(baseText, theirsText);

            Object[] results = dmp.patch_apply(minePatches, baseText);
            String resultText = (String) results[0];
//            boolean[] resultBool = (boolean[]) results[1];
            results = dmp.patch_apply(theirsPatches, resultText);
            resultText = (String) results[0];
            outputTextFile(merged+"_step1", resultText);
//            resultBool = (boolean[]) results[1];
            Json2XlsParser jParser = new Json2XlsParser(resultText);
            Workbook wb = jParser.toExcel();
            FileOutputStream fout = new FileOutputStream(merged);
            wb.write(fout);
            fout.close();
            wb.close();
            outputTextFile(merged+"_step2", resultText);
        } catch (Exception e) {
            throw new Exception("自动合并失败,请手动合并", e);
        } finally {
            outputTextFile(base, baseText);
            outputTextFile(theirs, theirsText);
            outputTextFile(mine, mineText);
        }
    }

    public static boolean outputTempFile = false;

    private static void outputTextFile(String merged, String resultText) throws IOException {
        if (!outputTempFile) {
            return;
        }
        String mergedTextFile = merged + ".json";
        FileWriter fileWriter = new FileWriter(mergedTextFile);
        fileWriter.write(resultText);
        fileWriter.close();
    }

}
