package exMerge.bean;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

public class SheetMetaBean {
    private ArrayList<Short> heights;
    private ArrayList<Integer> widths;

    public SheetMetaBean()  {
        super();
    }

    public SheetMetaBean(Sheet sheet) {
        this.heights = new ArrayList<>();
        this.widths = new ArrayList<>();

        int rowNum = sheet.getLastRowNum();

        int maxCol = 0;
        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            this.heights.add(row.getHeight());
            if(row.getLastCellNum() > maxCol) {
                maxCol = row.getLastCellNum();
            }
        }
        for (int i = 0; i < maxCol; i++) {
            this.widths.add(sheet.getColumnWidth(i));
        }
    }

    public ArrayList<Short> getHeights() {
        return heights;
    }

    public void setHeights(ArrayList<Short> heights) {
        this.heights = heights;
    }

    public ArrayList<Integer> getWidths() {
        return widths;
    }

    public void setWidths(ArrayList<Integer> widths) {
        this.widths = widths;
    }
}
