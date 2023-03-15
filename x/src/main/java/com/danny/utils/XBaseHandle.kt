package com.danny.utils

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

/**
 * Handle基类
 *
 * @author danny
 * @since 2022/9/9
 */
abstract class XBaseHandle<T> : Handler {
    private var mWeakReference: WeakReference<T>

    constructor(`object`: T) {
        mWeakReference = WeakReference(`object`)
    }

    constructor(looper: Looper, `object`: T) : super(looper) {
        mWeakReference = WeakReference(`object`)
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val obj: Any? = mWeakReference.get()
        if (!needHandleMessage(obj)) {
            removeCallbacksAndMessages(null as Any?)
        } else {
            handleWeakReferenceNotNull(msg)
        }
    }

    private fun needHandleMessage(obj: Any?): Boolean {
        if (obj == null) {
            return false
        }
        if (obj is Activity) {
            if (obj.isFinishing || obj.isDestroyed) {
                return false
            }
        }
        return obj !is Fragment || obj.isAdded
    }

    protected abstract fun handleWeakReferenceNotNull(msg: Message?)
}