package exMerge.bean;

import org.apache.poi.ss.usermodel.*;

public class CommentBean {
    private int colIndex;  // 列索引
    private int rowIndex;  // 行索引
    private String commentString; // Comment String
    private String author; // 作者
    private AnchorBean anchorBean;

    public CommentBean() {
        super();
    }

    public CommentBean(Comment comment) {
        this.rowIndex = comment.getRow();
        this.colIndex = comment.getColumn();
        this.commentString = comment.getString().getString();
        this.author = comment.getAuthor();
        ClientAnchor clientAnchor = comment.getClientAnchor();
        this.anchorBean = new AnchorBean(clientAnchor);
    }

    public Comment createComment(Workbook wb, Drawing drawing) {
        CreationHelper helper = wb.getCreationHelper();
        AnchorBean anchorBean = getAnchorBean();
        Comment comment = drawing.createCellComment(anchorBean.createAnchor(helper));
        comment.setRow(rowIndex);
        comment.setColumn(colIndex);
        comment.setString(helper.createRichTextString(commentString));
        comment.setAuthor(author);
        return comment;
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

    public AnchorBean getAnchorBean() {
        return anchorBean;
    }

    public void setAnchorBean(AnchorBean anchorBean) {
        this.anchorBean = anchorBean;
    }
}
