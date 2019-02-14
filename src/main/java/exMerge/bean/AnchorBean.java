package exMerge.bean;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;

public class AnchorBean {
    private int dx1;
    private int dx2;
    private int dy1;
    private int dy2;
    private short col1;
    private short col2;
    private int row1;
    private int row2;


    public AnchorBean() {
        super();
    }

    public AnchorBean(ClientAnchor anchor) {
        this.row1 = anchor.getRow1();
        this.col1 = anchor.getCol1();
        this.row2 = anchor.getRow2();
        this.col2 = anchor.getCol2();
        this.dx1 = anchor.getDx1();
        this.dx2 = anchor.getDx2();
        this.dy1 = anchor.getDy1();
        this.dy2 = anchor.getDy2();
    }

    public ClientAnchor createAnchor(CreationHelper helper) {
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setRow1(this.row1);
        anchor.setCol1(this.col1);
        anchor.setRow2(this.row2);
        anchor.setCol2(this.col2);
        anchor.setDx1(this.dx1);
        anchor.setDy1(this.dy1);
        anchor.setDx2(this.dx2);
        anchor.setDy2(this.dy2);
        return anchor;
    }

    public short getCol1() {
        return col1;
    }

    public void setCol1(short col1) {
        this.col1 = col1;
    }

    public short getCol2() {
        return col2;
    }

    public void setCol2(short col2) {
        this.col2 = col2;
    }

    public int getRow1() {
        return row1;
    }

    public void setRow1(int row1) {
        this.row1 = row1;
    }

    public int getRow2() {
        return row2;
    }

    public void setRow2(int row2) {
        this.row2 = row2;
    }

    public int getDx1() {
        return dx1;
    }

    public void setDx1(int dx1) {
        this.dx1 = dx1;
    }

    public int getDx2() {
        return dx2;
    }

    public void setDx2(int dx2) {
        this.dx2 = dx2;
    }

    public int getDy1() {
        return dy1;
    }

    public void setDy1(int dy1) {
        this.dy1 = dy1;
    }

    public int getDy2() {
        return dy2;
    }

    public void setDy2(int dy2) {
        this.dy2 = dy2;
    }
}
