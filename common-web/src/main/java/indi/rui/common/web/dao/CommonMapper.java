package indi.rui.common.web.dao;

import indi.rui.common.base.dto.QueryRequest;
import indi.rui.common.base.field.IFieldId;

import java.util.List;

public interface CommonMapper<Entity> extends IMapper {
    void add(Entity entity);
    void update(Entity entity);
    Integer findTotalNum(QueryRequest queryRequest);
    List<Entity> findAll(QueryRequest queryRequest);
    Entity findById(IFieldId iFieldId);
    void delete(IFieldId iFieldId);
}
