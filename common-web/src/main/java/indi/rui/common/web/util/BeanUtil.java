package indi.rui.common.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
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
    /**
     * 拷贝属性
     *
     * @param source
     * @param klass
     * @param <S>
     * @param <T>
     * @return
     */
    public static  <S, T> T copyProperties(S source, Class<T> klass, String... ignoreProperties) {
        try {
            Assert.notNull(source, "source不能为空");
            Assert.notNull(klass, "class不能为空");
            T target = klass.newInstance();
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static  <S, T> List<T> copyPropertiesForList(List<S> sources, Class<T> klass, String... ignoreProperties) {
        List<T> targets = new ArrayList<>();
        if (CollectionUtils.isEmpty(sources)) {
            return targets;
        }
        for (S source : sources) {
            targets.add(copyProperties(source, klass, ignoreProperties));
        }
        return targets;
    }
}
