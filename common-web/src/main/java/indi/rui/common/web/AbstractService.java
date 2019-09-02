package indi.rui.common.web;

import indi.rui.common.base.dto.*;
import indi.rui.common.base.field.IFieldId;
import indi.rui.common.base.field.IFieldIds;
import indi.rui.common.base.util.RandomUtil;
import indi.rui.common.base.util.SnowflakeIDGenerator;
import indi.rui.common.base.util.StringUtil;
import indi.rui.common.web.dao.IMapper;
import indi.rui.common.web.util.BeanUtil;
import indi.rui.common.web.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public abstract class AbstractService<M extends IMapper, E extends AbstractEntity, V extends AbstractVO> implements IApi<V> {
    protected M mapper;

    @Override
    public void add(V vo) {
        E entity = BeanUtil.copyProperties(vo, getEntityClass());
        if (StringUtil.isEmpty(entity.getId())) {
            entity.setId(String.valueOf(SnowflakeIDGenerator.genId()));
        }
        mapper.add(entity);
    }

    @Transactional
    @Override
    public void edit(V vo) {
        E entity = BeanUtil.copyProperties(vo, getEntityClass());
        mapper.update(entity);
    }

    @Override
    public QueryResult<V> list(QueryRequest queryRequest) {
        Integer total = mapper.findTotalNum(queryRequest);
        List<E> entities = mapper.findAll(queryRequest);
        List<V> vos = BeanUtil.copyPropertiesForList(entities, getVoClass());
        return new QueryResult<>(total, vos);
    }

    @Override
    public V get(IFieldId fieldId) {
        return BeanUtil.copyProperties(mapper.findById(fieldId), getVoClass());
    }

    @Override
    public void delete(IFieldIds fieldIds) {
        List<String> ids = fieldIds.getIds();
        if (ids != null && !ids.isEmpty()) {
            for (String id : ids) {
                mapper.delete(IdVO.ofId(id));
            }
        }
    }

    protected Class<E> getEntityClass() {
        return (Class<E>) ReflectUtil.getActualType(this.getClass(), "E");
    }

    protected Class<V> getVoClass() {
        return (Class<V>) ReflectUtil.getActualType(this.getClass(), "V");
    }

    protected abstract void setMapper(M mapper);
}
