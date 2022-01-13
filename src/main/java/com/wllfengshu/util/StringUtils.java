package com.wllfengshu.util;

/**
 * 字符串工具类
 *
 * @author wangll
 * @date 2022-01-13 22:43
 */
public class StringUtils {

    /**
     * 是否为空
     *
     * @param val
     * @return
     */
    public static boolean isNotEmpty(String val) {
        if (null == val || val.length() == 0) {
            return false;
        }
        return true;
    }
}
