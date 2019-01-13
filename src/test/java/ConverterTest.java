import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void toText() throws Exception {
        Converter converter = new Converter("src\\main\\resources\\z组合boss.xlsx");
        System.out.println(converter.toText());
    }
}