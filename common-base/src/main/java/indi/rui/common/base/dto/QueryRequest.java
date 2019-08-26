package indi.rui.common.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class QueryRequest<V extends IData> {
    private Integer pageSize;
    private Integer current;
    private V conditions;
    private Map<String, Object> sorters = new HashMap<String, Object>();

    public Integer getOffset() {
        return (current - 1) * pageSize;
    }
}
