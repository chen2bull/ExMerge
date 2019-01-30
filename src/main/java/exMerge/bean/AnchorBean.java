package exMerge.bean;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Workbook;

public class AnchorBean {
    private short col1;
    private short col2;
    private int row1;
    private int row2;


    public AnchorBean() {
        super();
    }

    public AnchorBean(ClientAnchor anchor) {
        this.col1 = anchor.getCol1();
        this.col2 = anchor.getCol2();
        this.row1 = anchor.getRow1();
        this.row2 = anchor.getRow2();
    }

    public ClientAnchor createAnchor(CreationHelper helper) {
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(this.col1);
        anchor.setCol2(this.col2);
        anchor.setRow1(this.row1);
        anchor.setRow2(this.row2);
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
}
