package indi.rui.common.web.util;

import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ReflectUtil {
    public static Class<?> getActualType(Class<?> cls, String parName) {
        Assert.notNull(cls, "class不能为空");
        Assert.notNull(parName, "parName不能为空");
        ParameterizedType pt = (ParameterizedType) cls.getGenericSuperclass();
        Type rt = pt.getRawType();
        TypeVariable[] tvs = ((Class<?>)rt).getTypeParameters();
        int idx = 0;
        for (; idx < tvs.length; idx++) {
            if (parName.equals(tvs[idx].getName())) {
                break;
            }
        }
        Type[] types = pt.getActualTypeArguments();
        if (idx >= types.length) {
            return null;
        }
        return (Class<?>)types[idx];
    }
}
