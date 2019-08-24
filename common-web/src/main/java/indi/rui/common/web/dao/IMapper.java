package indi.rui.common.web.dao;

import indi.rui.common.base.dto.QueryRequest;
import indi.rui.common.base.field.IFieldId;

import java.util.List;

public interface IMapper<E> {
    void add(E e);
    void update(E e);
    Integer findTotalNum(QueryRequest queryRequest);
    List<E> findAll(QueryRequest queryRequest);
    E findById(IFieldId iFieldId);
    void delete(IFieldId iFieldId);
}
