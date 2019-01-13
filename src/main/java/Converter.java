import org.apache.poi.ss.usermodel.*;


public class Converter {
    private String fname;

    public Converter(String fname) {
        this.fname = fname;
    }

    public String toText() throws Exception {
        Workbook wb = WorkBookUtil.open_workbook(this.fname);
        StringBuilder sb = new StringBuilder();
        for (Sheet sheet: wb) {
            sb.append(sheet.getSheetName());
            sb.append("\n");
        }
        WorkBookUtil.close_workbook(wb);
        return sb.toString();
    }
}
