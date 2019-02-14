package exMerge.parser;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exMerge.bean.StyleBean;
import org.apache.poi.ss.usermodel.CellStyle;

public class StylePool {
    private Map<String, CellStyle> styleMap = new HashMap<>();
    private ObjectMapper mapper = new ObjectMapper();

    public StylePool() {
    }

    public CellStyle getStyle(String name) {
        return this.styleMap.get(name);
    }

    public boolean isExist(String styleString) {
        return styleMap.containsKey(styleString);
    }

    public void updateStyle(String styleString, CellStyle style) {
        styleMap.put(styleString, style);
    }

    public String calcStyleString(StyleBean styleBean) throws JsonProcessingException {
        return this.mapper.writeValueAsString(styleBean);
    }

}
