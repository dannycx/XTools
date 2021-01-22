package com.danny.xtools.utils;

import androidx.collection.SimpleArrayMap;

import com.danny.xtools.utils.data.XSP;

import java.lang.reflect.Constructor;

/**
 * 反射工具类
 *
 * @author danny
 * @since 2020-11-27
 */
public class ReflectUtil {
    private static SimpleArrayMap<String, XSP> sam = new SimpleArrayMap<>();


    public static Object getReflectConstructor(Class clazz, Object innerClassInstance) {
        Object o = null;
        if (null == clazz) {
            return null;
        }
        Constructor<?>[] c = clazz.getDeclaredConstructors();
        c[0].setAccessible(true);
        try {
            o = c[0].newInstance(innerClassInstance);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return o;
    }

    public static boolean classExists(String className) {
        boolean flag;
        try {
            Class.forName(className);
            flag = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
