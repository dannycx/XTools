package com.danny.base

import android.os.Bundle
import android.util.SparseArray
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 绑定数据的Activity
 */
abstract class XBaseBindingActivity: FragmentActivity() {
    private val activityVMProvider: ViewModelProvider by lazy { ViewModelProvider(this) }

    lateinit var thisBinding: ViewDataBinding

    abstract fun getBindingConfig(): XBindingConfig

    abstract fun initVM()

    abstract fun initData()

    open fun initView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVM()
        val config = getBindingConfig()
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, config.layout)
        binding.lifecycleOwner = this
        if (null != config.viewModel) {
            binding.setVariable(config.variableId, config.viewModel)
        }

        val sa: SparseArray<*> = config.getBindingConfig()
        val length = sa.size()
        for (i in 0 until length) {
            binding.setVariable(sa.keyAt(i), sa.valueAt(i))
        }
        thisBinding = binding
        initView()
        initData()
    }

    /**
     * 获取activity级别VM
     */
    fun <T : ViewModel> getActivityVM(clazz: Class<T>): T = activityVMProvider[clazz]

    /**
     * 获取application级别VM
     */
    fun <T : ViewModel> getApplicationVM(clazz: Class<T>): T = (application as com.danny.base.XBaseApplication).vMProvider[clazz]
}
