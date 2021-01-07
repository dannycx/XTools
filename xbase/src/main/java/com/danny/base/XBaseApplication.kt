package com.danny.xtools.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * 获取应用级的ViewModel
 */
class XBaseApplication: Application(), ViewModelStoreOwner {
    private val vms: ViewModelStore by lazy { ViewModelStore() }

    private val factory: ViewModelProvider.Factory by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }

    val vMProvider: ViewModelProvider by lazy { ViewModelProvider(this, factory) }

    override fun getViewModelStore(): ViewModelStore = vms
}
