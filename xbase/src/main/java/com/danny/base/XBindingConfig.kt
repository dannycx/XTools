package com.danny.xtools.base

import android.util.SparseArray
import androidx.lifecycle.ViewModel

/**
 * dataBinding配置项
 *
 * @author danny
 * @since 2020-12-10
 */
class XBindingConfig(var layout: Int = 0, var variableId: Int = 0, var viewModel: ViewModel? = null) {
    private val sa: SparseArray<Any> = SparseArray()

    fun addParams(variableId: Int, `object`: Any): XBindingConfig {
        if (null == sa[variableId]) {
            sa.put(variableId, `object`)
        }
        return this
    }

    fun getBindingConfig(): SparseArray<Any> = sa
}
