package com.danny.jetpack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * dataBinding适配器基类
 *
 * @author danny
 * @since 2020-12-12
 */
abstract class XBaseBindingAdapter<M, B: ViewDataBinding>(
    var context: Context,
    diffCallback: DiffUtil.ItemCallback<M>
): ListAdapter<M, RecyclerView.ViewHolder>(diffCallback) {
    var onClick: OnItemClick<M>? = null
    var onLongClick: OnItemLongClick<M>? = null
    var onFocusListener: OnItemFocusListener? = null

    override fun submitList(list: MutableList<M>?) {
        super.submitList(list) {
            super.submitList(
                if (list.isNullOrEmpty()) ArrayList()
                else list
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(context),
            getLayoutId(viewType),
            parent,
            false
        )

        val holder = XBaseBindingHolder(binding.root)
        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            onClick?.onItemClick(getItem(position), position)
        }

        holder.itemView.setOnLongClickListener {
            val position = holder.bindingAdapterPosition
            if (null != onLongClick) {
                onLongClick!!.onItemLongClick(getItem(position), position)
                return@setOnLongClickListener true
            }
            false
        }

        holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { view, onFocus ->
            onFocusListener?.onItemCheckClick(
                    view,
                    onFocus,
                    0
            )
        }
        return holder
    }

    class XBaseBindingHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView)

    abstract fun getLayoutId(viewType: Int): Int

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<B>(holder.itemView)
        binding?.let {
            bindData(it, getItem(position), holder)
            it.executePendingBindings()
        }
    }

    abstract fun bindData(binding: B, item: M, holder: RecyclerView.ViewHolder)

    interface OnItemClick<M> {
        fun onItemClick(item: M, position: Int)
    }

    interface OnItemLongClick<M> {
        fun onItemLongClick(item: M, position: Int)
    }

    interface OnItemFocusListener {
        fun onItemCheckClick(v: View, focus: Boolean, position: Int)
    }
}
