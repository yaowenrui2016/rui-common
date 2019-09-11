package indi.rui.common.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询请求封装类
 */
@Getter
@Setter
public class QueryRequest<V extends AbstractVO> {
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
    private V conditions;

    /**
     * 排序字段
     */
    private Map<String, Object> sorters = new HashMap<String, Object>();

    public Integer getOffset() {
        return (current - 1) * pageSize;
    }
}
