package com.danny.utils;

import java.util.List;

/**
 * @author danny
 * @since 2021/9/16
 */
public class XCollectionsUtil {
    public boolean isMatch(Object object, Class cls) {
        if (object == null || cls == null) {
            return false;
        }
        if (!(object instanceof List)) {
            return false;
        }
        List<Object> list = (List<Object>) object;
        for (Object obj : list) {
            if (cls.asSubclass(obj.getClass())) {
                continue;
            }
        }
        return false;
    }
}
