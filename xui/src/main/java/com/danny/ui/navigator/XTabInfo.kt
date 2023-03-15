package com.danny.ui.navigator

/**
 * 底部导航栏配置
 *
 * @author danny
 * @since 2021/1/22
 */
class XTabInfo {
    var mIconNormalResId = 0
    var mIconPressedResId = 0
    var mNormalColor = 0
    var mSelectColor = 0
    var mText: String? = null

    fun setText(text: String?): XTabInfo {
        mText = text
        return this
    }

    fun setNormalIcon(res: Int): XTabInfo {
        mIconNormalResId = res
        return this
    }

    fun setPressedIcon(res: Int): XTabInfo {
        mIconPressedResId = res
        return this
    }

    fun setColor(color: Int): XTabInfo {
        mNormalColor = color
        return this
    }

    fun setCheckedColor(color: Int): XTabInfo {
        mSelectColor = color
        return this
    }
}
