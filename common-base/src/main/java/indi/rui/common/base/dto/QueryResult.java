package indi.rui.common.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 查询结果封装类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryResult<V> {
    /**
     * 页面条数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 内容
     */
    private List<V> content;
}
