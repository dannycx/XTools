package com.danny.xtools.keysoft

import android.app.Activity
import com.danny.xtools.keysoft.XSoftKeyChangedCallback
import com.danny.xtools.keysoft.XSoftKeyListener

/**
 * 键盘变化监听
 */
object XSoftKeyUtil {

    fun startSoftKeyListener(activity: Activity, callback: XSoftKeyChangedCallback) {
        val softKeyListener = XSoftKeyListener(activity)
        softKeyListener.startListener()
        softKeyListener.setSoftKeyChangedCallback(callback)
    }
}