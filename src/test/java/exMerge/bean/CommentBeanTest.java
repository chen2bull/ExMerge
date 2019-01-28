package exMerge.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentBeanTest {
    @Test
    public void testCommentBeanInit() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CommentBean commentBean = objectMapper.readValue("{\n" +
                "  \"colIndex\": 100,\n" +
                "  \"rowIndex\": 200,\n" +
                "  \"commentString\": \"You can pass\",\n" +
                "  \"author\": \"chenmj\"\n" +
                "}", CommentBean.class);
        assertEquals(commentBean.getColIndex(), 100);
        assertEquals(commentBean.getRowIndex(), 200);
        assertEquals(commentBean.getCommentString(), "You can pass");
        assertEquals(commentBean.getAuthor(), "chenmj");
        System.out.println(commentBean);
    }
}