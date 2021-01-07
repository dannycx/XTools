package com.danny.ui.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*

/**
 * recyclerView自定义适配器
 *
 * @author danny
 * @since 2020/12/14
 */

@BindingAdapter(value = ["adapter", "submitList", "scrollToTop", "scrollToBottom", "orientation"], requireAll = false)
fun bindList(
    recyclerView: RecyclerView?,
    adapter: ListAdapter<*, *>?,
    list: List<*>?,
    scrollToTop: Boolean,
    scrollToBottom: Boolean,
    orientation: Boolean
) {
    if (recyclerView != null && null != list) {
        when(recyclerView.layoutManager) {
            is LinearLayoutManager -> {

            }
            is GridLayoutManager -> {

            }
            is StaggeredGridLayoutManager -> {

            }
        }
        adapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
            }
        })
    }
    list?.let {  }
}

@BindingAdapter("notifyDataChanged", requireAll = false)
fun notifyDataChanged(recycler: RecyclerView?, notify: Boolean?) {
    recycler?.let {
//        if ()
    }
}