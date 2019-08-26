package indi.rui.common.web;

import indi.rui.common.base.dto.QueryRequest;
import indi.rui.common.base.dto.QueryResult;
import indi.rui.common.base.field.IFieldId;
import indi.rui.common.base.field.IFieldIds;

import java.util.List;

public interface IApi<V> {
    void add(V vo);
    void edit(V vo);
    QueryResult<V> list(QueryRequest queryRequest);
    V get(IFieldId fieldId);
    void delete(IFieldIds fieldIds);
}
