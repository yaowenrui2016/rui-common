package indi.rui.common.base.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryRequest {
    private Integer pageSize;
    private Integer currentPage;
}
