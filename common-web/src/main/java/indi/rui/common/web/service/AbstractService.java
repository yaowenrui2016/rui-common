package indi.rui.common.web.service;

import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class AbstractService<M, E, V> {

    protected void entityToVo(E e, V v) {

        BeanUtils.copyProperties(e, v);
    }

    protected void voToEntity(V v, E e) {
        BeanUtils.copyProperties(v, e);
    }
}
