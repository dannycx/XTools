package com.danny.base.activity

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat

/**
 * activity分发
 *
 * @author danny
 * @since 2022/8/31
 */
class XModuleContext {
    private var context: Activity? = null
    private var saveInstance: Bundle? = null

    /**
     * 保存模块ViewGroup列表
     */
    private var viewGroups: SparseArrayCompat<ViewGroup> = SparseArrayCompat()

    fun setActivity(context: Activity) {
        this.context = context
    }

    fun setSaveInstance(saveInstance: Bundle?) {
        this.saveInstance = saveInstance
    }

    fun setViewGroups(viewGroups: SparseArrayCompat<ViewGroup>) {
        this.viewGroups = viewGroups
    }
}
