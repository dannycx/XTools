package com.danny.xtools.util

import android.text.InputFilter
import kotlin.experimental.and

/**
 * java判断字符串是否为数字或中文或字母
 */
object XStringUtil {

    /**
     * 判断一个字符串的首字符是否为字母
     *
     * @param s 字符
     * @return true-是
     */
    fun initialAlphabet(s: String): Boolean {
        val c = s[0]
        val i = c.toInt()
        return i in 65..90 || i in 97..122
    }

    fun initialAlphabet2(s: String): Boolean {
        val c = s[0]
        return c in 'a'..'z' || c in 'A'..'Z'
    }

    /**
     * 3 .判断是否为汉字
     *
     * @param str
     * @return
     */
    fun isChinese(str: String): Boolean {
        val chars = str.toCharArray()
        var isGB2312 = false
        for (i in chars.indices) {
            val bytes = ("" + chars[i]).toByteArray()
            if (bytes.size == 2) {
                val ints = IntArray(2)
                ints[0] = (bytes[0] and 0xff.toByte()).toInt()
                ints[1] = (bytes[1] and 0xff.toByte()).toInt()
                if (ints[0] in 0x81..0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
                    isGB2312 = true
                    break
                }
            }
        }
        return isGB2312
    }

    /**
     * 输入框过滤器
     */
    val filter = InputFilter { source, start, end, _, _, _ ->
        for (i in (start until end)) {
            if (isChinese(source.elementAt(i))) {
                return@InputFilter ""
            }
        }
        return@InputFilter null
    }

    /**
     * 是否是汉字
     */
    private fun isChinese(c: Char): Boolean {
        val ub = Character.UnicodeBlock.of(c)
        return ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub === Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub === Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub === Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
    }

    fun isEmpty(args: Array<Any>?): Boolean = (null == args || args.isEmpty())

    fun isEmpty(str: String?): Boolean = (null == str || str.isEmpty() || "null" == str)
}
