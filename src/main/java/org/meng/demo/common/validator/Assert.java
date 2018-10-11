package org.meng.demo.common.validator;

import org.apache.commons.lang.StringUtils;
import org.meng.demo.common.exception.DemoException;

/**
 * 数据校验
 *
 * @author
 * @date 2018年09月28日09:32:15
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new DemoException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new DemoException(message);
        }
    }
}
