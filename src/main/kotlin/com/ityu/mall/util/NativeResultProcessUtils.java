package com.ityu.mall.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

import javax.persistence.Tuple;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static com.ityu.mall.util.ItyuUtilKt.fieldToProperty;
import static com.ityu.mall.util.ItyuUtilKt.propertyToField;

public class NativeResultProcessUtils {

    /**
     * tuple转实体对象
     *
     * @param source      tuple对象
     * @param targetClass 目标实体class
     * @param <T>         目标实体类型
     * @return 目标实体
     */
    public static <T> T processResult(Tuple source, Class<T> targetClass) {
        Object instantiate = BeanUtils.instantiate(targetClass);
        convertTupleToBean(source, instantiate, null);
        return (T) instantiate;
    }

    /**
     * tuple转实体对象
     *
     * @param source           tuple对象
     * @param targetClass      目标实体class
     * @param <T>              目标实体类型
     * @param ignoreProperties 要忽略的属性
     * @return 目标实体
     */
    public static <T> T processResult(Tuple source, Class<T> targetClass, String... ignoreProperties) {
        Object instantiate = BeanUtils.instantiate(targetClass);
        convertTupleToBean(source, instantiate, ignoreProperties);
        return (T) instantiate;
    }

    /**
     * 把tuple中属性名相同的值复制到实体中
     *
     * @param source tuple对象
     * @param target 目标对象实例
     */
    public static void convertTupleToBean(Tuple source, Object target) {
        convertTupleToBean(source, target, null);
    }

    /**
     * 把tuple中属性名相同的值复制到实体中
     *
     * @param source           tuple对象
     * @param target           目标对象实例
     * @param ignoreProperties 要忽略的属性
     */
    public static void convertTupleToBean(Tuple source, Object target, String... ignoreProperties) {
        //目标class
        Class<?> actualEditable = target.getClass();
        //获取目标类的属性信息
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        //忽略列表
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        //遍历属性节点信息
        for (PropertyDescriptor targetPd : targetPds) {
            //获取set方法
            Method writeMethod = targetPd.getWriteMethod();
            //判断字段是否可以set
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                //获取source节点对应的属性
                String propertyName = targetPd.getName();
                propertyName = propertyToField(propertyName);
                Object value = null;
                try {
                    value = source.get(propertyName);
                } catch (Exception e) {
                    continue;
                }
                if (value instanceof BigInteger) {
                    value = ((BigInteger) value).longValue();
                }
                if (value != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], value.getClass())) {
                    try {
                        //判断target属性是否private
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        //写入target
                        writeMethod.invoke(target, value);
                    } catch (Throwable ex) {
                        throw new FatalBeanException(
                                "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                    }
                }
            }
        }
    }

}

