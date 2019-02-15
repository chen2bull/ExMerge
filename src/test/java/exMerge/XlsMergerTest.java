package exMerge;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

public class XlsMergerTest {

    @Test
    public void mainTest() throws Exception {
        String[] files = {"p抽取base.xls", "p抽取theirs.xls", "p抽取mine.xls", "p抽取merged.xls"};
        String[] fileFullPaths = new String[4];
        for (int i = 0; i < files.length; i++) {
            fileFullPaths[i] = String.join(File.separator, "src", "test", "resources", files[i]);
        }
        System.out.println(Arrays.toString(fileFullPaths));
        XlsMerger.outputTempFile = true;
        XlsMerger.main(fileFullPaths);
    }

    @Test
    public void mainTest2() throws Exception {
        String[] files = {"f副本奖励base.xls", "f副本奖励theirs.xls", "f副本奖励mine.xls", "f副本奖励merged.xls"};
        String[] fileFullPaths = new String[4];
        for (int i = 0; i < files.length; i++) {
            fileFullPaths[i] = String.join(File.separator, "src", "test", "resources", files[i]);
        }
        System.out.println(Arrays.toString(fileFullPaths));
        XlsMerger.outputTempFile = true;
        int retCode = XlsMerger.main(fileFullPaths);
        assertEquals(retCode, 0);
    }
}