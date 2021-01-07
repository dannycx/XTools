package com.danny.ui.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danny.XConst
import com.danny.XLog

/**
 * 防止越界
 *
 * @author danny
 * @since 2020-12-12
 */
class XWrapGridLayoutManager: GridLayoutManager {
    companion object{
        private val TAG = XWrapStaggeredGridLayoutManager::javaClass.name
    }

    constructor(context: Context?, spanCount: Int): super(context, spanCount)

    constructor(context: Context?, spanCount: Int, @RecyclerView.Orientation orientation: Int,
                reverseLayout: Boolean): super(context, spanCount, orientation, reverseLayout)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int,
                defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            XLog.e(XConst.TAG + TAG, e.toString())
        }
    }
}
