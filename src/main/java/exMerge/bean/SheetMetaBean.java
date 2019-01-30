package exMerge.bean;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

public class SheetMetaBean {
    private ArrayList<Short> rowHeightList;
    private ArrayList<Short> colWidthList;

    public SheetMetaBean()  {
        super();
    }

    public SheetMetaBean(Sheet sheet) {
        this.rowHeightList = new ArrayList<>();
        this.colWidthList = new ArrayList<>();

        int rowNum = sheet.getLastRowNum();
        Row rowHasMaxColumn = sheet.getRow(0);
        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            this.rowHeightList.add(row.getHeight());
            if(rowHasMaxColumn.getLastCellNum() < row.getLastCellNum()) {

            }
        }


    }

    public ArrayList<Short> getColWidthList() {
        return colWidthList;
    }

    public void setColWidthList(ArrayList<Short> colWidthList) {
        this.colWidthList = colWidthList;
    }

    public ArrayList<Short> getRowHeightList() {
        return rowHeightList;
    }

    public void setRowHeightList(ArrayList<Short> rowHeightList) {
        this.rowHeightList = rowHeightList;
    }
}
