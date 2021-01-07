package com.danny.xtools.base

import android.content.Context
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 绑定数据的Activity
 */
abstract class XBaseBindingFragment: Fragment() {
    private lateinit var thisActivity: FragmentActivity

    private val activityVMProvider: ViewModelProvider by lazy { ViewModelProvider(thisActivity) }

    private val fragmentVMProvider: ViewModelProvider by lazy { ViewModelProvider(this) }

    lateinit var thisBinding: ViewDataBinding

    abstract fun getBindingConfig(): XBindingConfig

    abstract fun initVM()

    abstract fun initData()

    open fun initView(view: View) {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        thisActivity = context as FragmentActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initVM()
        val config = getBindingConfig()
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, config.layout, container, false)
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
        initData()
        return thisBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    /**
     * 获取activity级别VM
     */
    fun <T : ViewModel> getActivityVM(clazz: Class<T>): T = activityVMProvider[clazz]

    /**
     * 获取fragment级别VM
     */
    fun <T : ViewModel> getFragmentVM(clazz: Class<T>): T = fragmentVMProvider[clazz]


    /**
     * 获取fragment级别VM
     */
    fun <T : ViewModel> getApplicationVM(clazz: Class<T>): T = (thisActivity.application as XBaseApplication).vMProvider[clazz]
}
