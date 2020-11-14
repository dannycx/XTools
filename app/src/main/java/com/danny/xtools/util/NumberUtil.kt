package com.danny.xtools.util

import java.util.regex.Pattern

/**
 * 数据类型转换
 *
 * @author danny
 * @since 2020-11-14
 */
object NumberUtil {

    /**
     * 字符转汉字
     */
    fun toInt(s: String): Int = try {
            s.toInt()
        } catch (e: NumberFormatException) {
            0
        }

    /**
     * 判断字符串是否仅为数字:
     *
     * @param str 原字符
     * @return true-是
     */
    fun isNumeric1(str: String): Boolean {
        var i = str.length
        while (--i >= 0) {
            if (!Character.isDigit(str[i])) {
                return false
            }
        }
        return true
    }

    /**
     * 用正则表达式--判断字符串是否仅为数字:
     *
     * @param str 原字符
     * @return true-是
     */
    fun isNumeric2(str: String): Boolean {
        val pattern: Pattern = Pattern.compile("[0-9]*")
        return pattern.matcher(str).matches()
    }

    /**
     * 用ascii码--判断字符串是否仅为数字:
     *
     * @param str 原字符
     * @return true-是
     */
    fun isNumeric3(str: String): Boolean {
        var i = str.length
        while (--i >= 0) {
            val chr = str[i].toInt()
            if (chr < 48 || chr > 57) return false
        }
        return true
    }
}
