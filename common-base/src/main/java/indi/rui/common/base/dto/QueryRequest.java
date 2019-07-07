package indi.rui.common.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class QueryRequest {
    private Integer pageSize;
    private Integer current;
    private Map<String,Object> conditions = new HashMap<String, Object>();
    private Map<String, Object> sorters = new HashMap<String, Object>();

    public Integer getOffset() {
        return (current - 1) * pageSize;
    }
}
