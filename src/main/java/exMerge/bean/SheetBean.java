package exMerge.bean;

import java.util.ArrayList;

public class SheetBean {
    private String sheetName;
    private SheetMetaBean meta;
    private ArrayList<ArrayList<CellBean>> content;

    public SheetBean(String sheetName, ArrayList<ArrayList<CellBean>> content) {
        this.sheetName = sheetName;
        this.content = content;
    }
    public SheetBean() {
        super();
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

    public SheetMetaBean getMeta() {
        return meta;
    }

    public void setMeta(SheetMetaBean meta) {
        this.meta = meta;
    }
}
