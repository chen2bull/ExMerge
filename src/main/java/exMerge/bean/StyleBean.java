package exMerge.bean;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

public class StyleBean {
    private short f; // 前景色
    private short b; // 后景色

    public StyleBean() {
        super();
    }

    public StyleBean(CellStyle cellStyle) {
        this.b = cellStyle.getFillBackgroundColor();
        this.f = cellStyle.getFillForegroundColor();
    }

    public boolean isDefaultStyle() {
        return getB() == 64 && getF() == 64;
    }

    public CellStyle createStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(this.getF());
        cellStyle.setFillBackgroundColor(this.getB());
        return cellStyle;
    }

    public short getF() {
        return f;
    }

    public void setF(short f) {
        this.f = f;
    }

    public short getB() {
        return b;
    }

    public void setB(short b) {
        this.b = b;
    }
}
