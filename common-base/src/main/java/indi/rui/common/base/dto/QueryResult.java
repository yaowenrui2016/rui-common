package indi.rui.common.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryResult<T> {
    private Integer totalSize;
    private Integer pageSize;
    private Integer currentPage;
    private List<T> content;
}
