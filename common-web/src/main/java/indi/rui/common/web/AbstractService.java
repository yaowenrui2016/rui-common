package indi.rui.common.web;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import indi.rui.common.base.dto.*;
import indi.rui.common.base.field.IFieldId;
import indi.rui.common.base.field.IFieldIds;
import indi.rui.common.base.util.SnowflakeIDGenerator;
import indi.rui.common.base.util.StringUtil;
import indi.rui.common.web.dao.IMapper;
import indi.rui.common.web.util.BeanUtil;
import indi.rui.common.web.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractService<M extends IMapper<E>, E extends AbstractEntity, V extends AbstractVO>
    implements IApi<V> {
    /**
     * 当前模型的mapper，由子类来注入
     */
    protected M mapper;

    @Transactional
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
    public QueryResult<V> list(QueryRequest request) {
        Integer total = mapper.findTotalNum(request);
        List<V> vos = BeanUtil.copyPropertiesForList(mapper.findAll(request), getVoClass());
        return new QueryResult<>(request.getPageSize(), request.getCurrent(), total, vos);
    }

    @Override
    public V get(IFieldId fieldId) {
        E entity = mapper.findById(fieldId);
        if (entity == null) {
            return null;
        }
        return BeanUtil.copyProperties(entity, getVoClass());
    }

    @Transactional
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
        return (Class<E>)ReflectUtil.getActualType(this.getClass(), "E");
    }

    protected Class<V> getVoClass() {
        return (Class<V>)ReflectUtil.getActualType(this.getClass(), "V");
    }

    /**
     * 由子类来注入
     * @param mapper
     */
    protected abstract void setMapper(M mapper);
}
