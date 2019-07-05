package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

@Slf4j
public class BeanUtil {
    public static <T> T buildBean(Map<String, Object> data, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                String property = propertyDescriptor.getName();
                if ("class".equals(property))
                    continue;
                propertyDescriptor.getWriteMethod().invoke(obj, data.get(property));
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
