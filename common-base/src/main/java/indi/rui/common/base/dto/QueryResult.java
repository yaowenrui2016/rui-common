package indi.rui.common.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryResult<T> {
    private Integer pageSize;
    private Integer currentPage;
    private List<T> data;
}
