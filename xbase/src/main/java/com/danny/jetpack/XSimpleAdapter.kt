package com.danny.jetpack

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

abstract class XSimpleAdapter<M, B: ViewDataBinding>(
    context: Context,
    private val layoutId: Int,
    diff: DiffUtil.ItemCallback<M>
): XBaseBindingAdapter<M, B>(context, diff) {

    override fun getLayoutId(viewType: Int): Int = layoutId
}
