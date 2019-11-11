package indi.rui.common.base.util;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.scanners.TypeElementsScanner;
import org.reflections.util.ConfigurationBuilder;

public class AnnotationScanUtil {

    public static Set<Class<?>> findTypesAnnotationedWith(Class baseClass, Class<? extends Annotation> annotationClass) {
        Reflections reflections = new Reflections(ConfigurationBuilder.build(
                baseClass,
                new TypeElementsScanner(),
                new SubTypesScanner(false),
                new MethodAnnotationsScanner(),
                new TypeAnnotationsScanner()
        ));
        return reflections.getTypesAnnotatedWith(annotationClass);
    }
}
