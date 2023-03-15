package com.danny.ui.navigator

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.danny.ui.R

/**
 * 底部导航栏
 *
 * @author danny
 * @since 2021/1/22
 */
class XNavigatorBar(context: Context?, attrs: AttributeSet?, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    private var tabViews: MutableList<View>? = null
    private var tabs: MutableList<XTabInfo>? = null
    private var mOnTabCheckListener: OnTabChangedListener? = null

    fun setOnTabCheckListener(onTabCheckListener: OnTabChangedListener?) {
        mOnTabCheckListener = onTabCheckListener
    }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        tabViews = ArrayList()
        tabs = ArrayList()
    }

    /**
     * 添加Tab
     * @param tab
     */
    fun addTab(tab: XTabInfo) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.x_tab_item, null)
        val textView: TextView = view.findViewById(R.id.x_tab_text) as TextView
        val imageView: ImageView = view.findViewById(R.id.x_tab_img) as ImageView
        imageView.setImageResource(tab.mIconNormalResId)
        textView.text = tab.mText
        textView.setTextColor(tab.mNormalColor)
        view.tag = tabViews!!.size
        view.setOnClickListener(this)
        tabViews!!.add(view)
        tabs!!.add(tab)
        addView(view)
    }

    /**
     * 设置选中Tab
     * @param position
     */
    fun setCurrentItem(position: Int) {
        var position = position
        if (position >= tabs!!.size || position < 0) {
            position = 0
        }
        tabViews!![position].performClick()
        updateState(position)
    }

    /**
     * 更新状态
     * @param position
     */
    private fun updateState(position: Int) {
        for (i in tabViews!!.indices) {
            val view: View = tabViews!![i]
            val textView: TextView = view.findViewById(R.id.x_tab_text) as TextView
            val imageView: ImageView = view.findViewById(R.id.x_tab_img) as ImageView
            if (i == position) {
                imageView.setImageResource(tabs!![i].mIconPressedResId)
                textView.setTextColor(tabs!![i].mSelectColor)
            } else {
                imageView.setImageResource(tabs!![i].mIconNormalResId)
                textView.setTextColor(tabs!![i].mNormalColor)
            }
        }
    }


    override fun onClick(v: View) {
        val position = v.tag as Int
        if (mOnTabCheckListener != null) {
            mOnTabCheckListener!!.onTabSelected(v, position)
        }
        updateState(position)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (tabViews != null) {
            tabViews!!.clear()
        }
        if (tabs != null) {
            tabs!!.clear()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // 调整每个Tab的大小
        for (i in tabViews!!.indices) {
            val view: View = tabViews!![i]
            val width = resources.displayMetrics.widthPixels / tabs!!.size
            val params = LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT)
            view.layoutParams = params
        }
    }
}
