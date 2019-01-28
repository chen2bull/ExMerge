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

class CommentBean {
    private int colIndex;  // 列索引
    private int rowIndex;  // 行索引
    private String commentString; // Comment String
    private String author; // 作者

    CommentBean(Comment comment) {
        this.rowIndex = comment.getRow();
        this.colIndex = comment.getColumn();
        this.commentString = comment.getString().getString();
        this.author = comment.getAuthor();
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

class StyleBean {
    private short f; // 前景色
    private short b; // 后景色

    static boolean isDefaultStyle(short b, short f) {
        return b == 64 && f == 64;
    }

    StyleBean(short b, short f) {
        this.b = b;
        this.f = f;
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
