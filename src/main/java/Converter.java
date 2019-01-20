import org.apache.poi.ss.usermodel.*;


public class Converter {
    private String fname;

    public Converter(String fname) {
        this.fname = fname;
    }

    public String toText() throws Exception {
        Workbook wb = WorkBookUtil.open_workbook(this.fname);
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        boolean isFirstSheet = true;
        for (Sheet sheet : wb) {
            if (!isFirstSheet) {
                sb.append(",\n");
            }
            isFirstSheet = false;
            sb.append("{ \"sheetName\":");
            sb.append("\"");
            sb.append(sheet.getSheetName().replace("\"", "\\\""));
            sb.append("\"");
            sb.append(",\"content\":[");
            sb.append(WorkBookUtil.calc_sheet_text(sheet));
            sb.append("]}");
        }
        sb.append("]\n");
        WorkBookUtil.close_workbook(wb);
        return sb.toString();
    }
}
