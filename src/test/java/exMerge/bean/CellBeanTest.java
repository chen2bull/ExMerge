package exMerge.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellBeanTest {
    @Test
    public void cellBeanInit() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CellBean cellBean = objectMapper.readValue("{\"t\":\"STRING\",\"v\":\"地底人\"}", CellBean.class);
        assertEquals(cellBean.getT(), CellType.STRING);
        assertEquals(cellBean.getV(), "地底人");

        //
        CellBean styleBean2 = objectMapper.readValue("{" +
                "\"t\":\"STRING\"," +
                "\"v\":\"地底人\", " +
                "\"s\":{\"f\":60,\"b\":50}}",
                CellBean.class);
        CommentBean commentBean = styleBean2.getC();
        assertNull(commentBean);
        assertEquals(styleBean2.getT(), CellType.STRING);
        assertEquals(styleBean2.getV(), "地底人");
        StyleBean styleBean = styleBean2.getS();
        assertEquals(styleBean.getF(), 60);
        assertEquals(styleBean.getB(), 50);


        CellBean cellBean3 = objectMapper.readValue("{\"t\":\"STRING\"," +
                "\"v\":\"地底人\", " +
                "\"s\": {\"colIndex\": 100,\n" +
                "\"rowIndex\": 200,\n" +
                "\"commentString\": \"You can pass\",\n" +
                "\"author\": \"chenmj\"\n" +
                "}}", CellBean.class);
        assertEquals(cellBean3.getT(), CellType.STRING);
        assertEquals(cellBean3.getV(), "地底人");


    }

}