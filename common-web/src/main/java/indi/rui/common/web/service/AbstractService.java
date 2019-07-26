package indi.rui.common.web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class AbstractService{
    /**
     * 拷贝属性
     *
     * @param source
     * @param klass
     * @param <S>
     * @param <T>
     * @return
     */
    protected <S, T> T copyProperties(S source, Class<T> klass, String... ignoreProperties) {
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

    protected <S, T> List<T> copyPropertiesForList(List<S> sources, Class<T> klass, String... ignoreProperties) {
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
