package com.danny.xtools.util

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan

/**
 * 文本工具类
 *
 * @author danny
 * @since 2020-12-02
 */
object TextUtil {

    fun textSpan(text: CharSequence, key: String, color: Int): SpannableStringBuilder {
        val ssb = SpannableStringBuilder(text)
        val fcs = ForegroundColorSpan(color)
        val index = text.indexOf(key)
        ssb.setSpan(fcs, index, index + key.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
    }
}