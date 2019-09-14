package indi.rui.common.base.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询请求封装类
 */
@Getter
@Setter
public class QueryRequest {
    /**
     * 页面条数
     */
    private Integer pageSize = new Integer(20);

    /**
     * 当前页码
     */
    private Integer current = new Integer(1);

    /**
     * 过滤条件
     */
    private Map<String, Object> conditions = new HashMap<>();

    /**
     * 排序字段
     */
    private Map<String, Object> sorters = new HashMap<String, Object>();

    public Integer getOffset() {
        return (current - 1) * pageSize;
    }
}
