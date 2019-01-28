package exMerge;

import org.apache.poi.ss.usermodel.Comment;

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
