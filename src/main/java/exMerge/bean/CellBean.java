package exMerge.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.poi.ss.usermodel.*;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * 很骚的命名,不是数学博士的话，一般都看不懂
 */
public class CellBean {
    private CellType t; // 类型
    private String v; // 值
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CommentBean c; // 浮动注释
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StyleBean s; // 格式

    public CellBean(Cell cell, DataFormatter formatter, boolean useFormulaCached) {
        this.t = cell.getCellType();
        this.v = formatter.formatCellValue(cell);
        if (useFormulaCached && this.t == FORMULA) {
            if (cell.getCachedFormulaResultType() == NUMERIC) {
                this.v = String.valueOf(cell.getNumericCellValue());
                this.t = NUMERIC;
            } else if (cell.getCachedFormulaResultType() == STRING) {
                this.v = cell.getStringCellValue();
                this.t = STRING;
            }
        }

        CellStyle cellStyle = cell.getCellStyle();
        StyleBean s = new StyleBean(cellStyle);
        if (!StyleBean.isDefaultStyle(s)) {
            this.s = s;
        }
        Comment comment = cell.getCellComment();
        if (comment != null) {
            this.c = new CommentBean(comment);
        }
    }

    public CellBean() {
        super();
    }

    public CellType getT() {
        return t;
    }

    public void setT(CellType cellType) {
        this.t = cellType;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public CommentBean getC() {
        return c;
    }

    public void setC(CommentBean c) {
        this.c = c;
    }

    public StyleBean getS() {
        return s;
    }

    public void setS(StyleBean s) {
        this.s = s;
    }
}

