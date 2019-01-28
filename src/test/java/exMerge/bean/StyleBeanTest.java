package exMerge.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StyleBeanTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInit() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        StyleBean styleBean = objectMapper.readValue("{\"f\":60,\"b\":50}", StyleBean.class);
        assertEquals(styleBean.getF(), 60);
        assertEquals(styleBean.getB(), 50);
    }
}