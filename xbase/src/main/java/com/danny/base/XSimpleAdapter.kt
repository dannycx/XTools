package com.danny.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

abstract class XSimpleAdapter<M, B: ViewDataBinding>(
    context: Context,
    private val layoutId: Int,
    diff: DiffUtil.ItemCallback<M>
): com.danny.base.XBaseBindingAdapter<M, B>(context, diff) {

    override fun getLayoutId(viewType: Int): Int = layoutId
}
