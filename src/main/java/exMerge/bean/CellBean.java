package exMerge.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;

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

    CellBean(CellType cellType, String cellString, Comment comment, CellStyle cellStyle) {
        this.t = cellType;
        this.v = cellString;
        short b = cellStyle.getFillBackgroundColor();
        short f = cellStyle.getFillForegroundColor();
        if (!StyleBean.isDefaultStyle(f, b)) {
            this.s = new StyleBean(f, b);
        }
        if (comment != null) {
            this.c = new CommentBean(comment);
        }
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

