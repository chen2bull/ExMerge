package exMerge.bean;

import java.util.ArrayList;

public class SheetBean {
    private String sheetName;
    private ArrayList<ArrayList<CellBean>> content;

    public SheetBean(String sheetName, ArrayList<ArrayList<CellBean>> content) {
        this.sheetName = sheetName;
        this.content = content;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public ArrayList<ArrayList<CellBean>> getContent() {
        return content;
    }

    public void setContent(ArrayList<ArrayList<CellBean>> content) {
        this.content = content;
    }
}
