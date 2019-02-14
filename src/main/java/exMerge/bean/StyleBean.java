package exMerge.bean;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

public class StyleBean {
    private short f; // 前景色
    private short b; // 后景色
    private short df; // DataFormat

    public StyleBean() {
        super();
    }

    public StyleBean(CellStyle cellStyle) {
        this.b = cellStyle.getFillBackgroundColor();
        this.f = cellStyle.getFillForegroundColor();
        this.df = cellStyle.getDataFormat();
    }

    public static boolean isDefaultStyle(StyleBean styleBean) {
        return styleBean.getB() == 64
                && styleBean.getF() == 64
                && styleBean.getDf() == 0; // 0是默认格式
    }

    public CellStyle createStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(this.getF());
        cellStyle.setFillBackgroundColor(this.getB());
        cellStyle.setDataFormat(this.getDf());
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

    public short getDf() {
        return df;
    }

    public void setDf(short df) {
        this.df = df;
    }
}
