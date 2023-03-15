package com.danny.utils

import android.app.Service
import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.WindowManager

/**
 * UI工具类
 *
 * @author danny
 * @since 2022/9/8
 */
object XUIUtil {
    /**
     * 是否大显示模式，默认值3.0
     */
    fun isLargeScaledDensity(context: Context, scaledDensity: Float) = getScaledDensity(context) > scaledDensity

    private fun getScaledDensity(context: Context) = context.resources.displayMetrics.scaledDensity

    /**
     * 是否大字体模式
     */
    fun isLargeFontSize(context: Context, fontSize: Float) = getFontSize(context) > fontSize

    private fun getFontSize(context: Context) = context.resources.configuration.fontScale

    /**
     * 控件居中
     */
    fun setViewCenter(view: View?) {
        view?.let {
            val wm = it.context.getSystemService(Service.WINDOW_SERVICE) as WindowManager
            val width = wm.defaultDisplay.width
            val height = wm.defaultDisplay.height
            val left = width / 2 - it.width / 2
            val top = height / 2 - it.height / 2
            val right = width / 2 + it.width / 2
            val bottom = height / 2 + it.height / 2
            it.layout(left, top, right, bottom)
        }
    }

    /**
     * 快速点击
     */
    private var lastTime = 0L
    fun isFastClick(delay: Long): Boolean {
        val current = System.currentTimeMillis()
        if (current - lastTime < delay) {
            return true
        }
        lastTime = current
        return false
    }

    /**
     * 是否三击事件
     */
    private val threeClick = LongArray(3)
    fun isThreeClick(delay: Long): Boolean {
        System.arraycopy(threeClick, 1, threeClick, 0, threeClick.size - 1)
        threeClick[threeClick.size - 1] = SystemClock.uptimeMillis()
        if (threeClick[threeClick.size - 1] - threeClick[0] < delay) {
            return true
        }
        return false
    }
}
