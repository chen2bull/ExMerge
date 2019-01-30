package exMerge.bean;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;

public class RowBean {
    private ArrayList<CellBean> cellBeanList;


    public RowBean() {
        super();
    }
    public RowBean(Row row) {
        this.cellBeanList = new ArrayList<>();
        int colNum = row.getLastCellNum();
        for (int c = 0; c < colNum; c++) {
            Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            CellBean cellBean = new CellBean(cell);
            cellBeanList.add(cellBean);
        }
    }

    public ArrayList<CellBean> getCellBeanList() {
        return cellBeanList;
    }

    public void setCellBeanList(ArrayList<CellBean> cellBeanList) {
        this.cellBeanList = cellBeanList;
    }
/*
    public RowBean(Row row) {
        boolean isFirstCell = true;
        int colNum = row.getLastCellNum();
        for (int c = 0; c < colNum; c++) {
            Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (!isFirstCell) {
                sb.append(",\n");
            }
            isFirstCell = false;
            CellBean cellBean = new CellBean(cell);
            sb.append(mapper.writeValueAsString(cellBean));
        }
    }
    */
}
