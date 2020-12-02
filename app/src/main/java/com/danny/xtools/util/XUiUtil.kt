package com.danny.xtools.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.danny.xtools.R

object XUiUtil {
    private const val RES_LAYOUT = "layout"
    private const val RES_ID = "id"
    private const val RES_STRING = "string"
    private const val RES_COLOR = "color"
    private const val RES_DRAWABLE = "drawable"
    private const val RES_MIPMAP = "mipmap"
    private const val RES_DIMEN = "dimen"
    private const val RES_STYLE = "style"
    private const val RES_ATTR = "attr"
    private const val RES_ANIM = "anim"

    /** 获取布局文件id  */
    fun getLayoutId(context: Context, name: String?): Int {
        return getResId(context, name, RES_LAYOUT)
    }

    /** 获取字符串资源id  */
    private fun getStringId(context: Context, name: String?): Int {
        var strId = getResId(context, name, RES_STRING)
        if (strId < 0) {
            strId = R.string.app_name
        }
        return strId
    }

    fun getString(context: Context, name: String?): String {
        return context.resources.getString(getStringId(context, name))
    }

    /** 获取drawable资源id  */
    fun getDrawableId(context: Context, name: String?): Int {
        return getResId(context, name, RES_DRAWABLE)
    }

    /** 获取mipmap资源id  */
    fun getMipmapId(context: Context, name: String?): Int {
        return getResId(context, name, RES_MIPMAP)
    }

    /** 获取mipmap/drawable/color资源id  */
    fun getMipmapDrawableColorId(context: Context, name: String?): Int {
        if (TextUtils.isEmpty(name)) {
            return R.mipmap.icon_notify
        }
        var resId = getMipmapId(context, name)
        if (resId == 0) {
            resId = getDrawableId(context, name)
        }
        if (resId == 0) {
            resId = getColorId(context, name)
        }
        return if (resId == 0) {
            R.color.color_eb5a5a
        } else resId
    }

    /** 获取color资源id  */
    fun getColorId(context: Context, name: String?): Int {
        return getResId(context, name, RES_COLOR)
    }

    /** 获取dimen资源id  */
    fun getDimenId(context: Context, name: String?): Int {
        return getResId(context, name, RES_DIMEN)
    }

    /** 获取style资源id  */
    fun getStyleId(context: Context, name: String?): Int {
        return getResId(context, name, RES_STYLE)
    }

    /** 获取attr资源id  */
    fun getAttrId(context: Context, name: String?): Int {
        return getResId(context, name, RES_ATTR)
    }

    /** 获取anim资源id  */
    fun getAnimId(context: Context, name: String?): Int {
        return getResId(context, name, RES_ANIM)
    }

    /** 获取dimen资源id  */
    fun getDimensionPixelSize(context: Context, name: String?): Int {
        return context.resources.getDimensionPixelSize(getResId(context, name, RES_DIMEN))
    }

    /** 获取dimen资源  */
    fun getDimensionPixelSize(context: Context, @DimenRes dimenId: Int): Int {
        return context.resources.getDimensionPixelSize(dimenId)
    }

    /**
     * 获取资源文件id
     *
     * @param context 上下文
     * @param name 文件名
     * @param defType 文件类型(layout,string,drawable,mipmap等)
     * @return 资源文件id
     */
    private fun getResId(context: Context, name: String?, defType: String?): Int {
        return context.resources.getIdentifier(name, defType, context.packageName)
    }

    /**
     * px转dp
     *
     * @param context 上下文对象
     * @param px 像素值
     * @return dp值
     */
    fun px2dp(context: Context, px: Int): Int {
        val density = context.resources.displayMetrics.density
        return (px / density + 0.5f).toInt()
    }

    /**
     * dp转px
     *
     * @param context 上下文对象
     * @param dp dp值
     * @return 像素值
     */
    fun dp2px(context: Context, dp: Float): Int {
        // 方式一
//        float density = context.getResources().getDisplayMetrics().density;
//        return (int) (dp * density + 0.5f);
        // 方式二
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }

    /**
     * sp转px
     *
     * @param context 上下文对象
     * @param sp sp值
     * @return 像素值
     */
    fun sp2Px(context: Context, sp: Int): Int {
        // 方式一
//        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (sp * scaledDensity + 0.5f);
        // 方式二
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), context.resources.displayMetrics).toInt()
    }

    /**
     * px转sp
     *
     * @param context 上下文对象
     * @param px 像素值
     * @return sp值
     */
    fun px2Sp(context: Context, px: Int): Int {
        val scaledDensity = context.resources.displayMetrics.scaledDensity
        return (px / scaledDensity + 0.5f).toInt()
    }

    /**
     * 获取屏幕宽高
     *
     * @param context 上下文对象
     * @return 屏幕宽高数组
     */
    fun getScreenDisplay(context: Context): IntArray? {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val width = display.width
        val height = display.height
        return intArrayOf(width, height)
    }

    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    fun getDisplayWidth(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.widthPixels
    }

    /**
     * 获取屏幕高度
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    fun getDisplayHeight(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.heightPixels
    }

    /**
     * 获取字符串资源
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return 字符串
     */
    fun getString(context: Context, resId: Int): String? {
        return context.resources.getString(resId)
    }

    /**
     * 获取字符串资源数组
     *
     * @param context 上下文对象
     * @param resId 字符串数组资源id
     * @return 字符串数组
     */
    fun getStringArray(context: Context, resId: Int): Array<String?>? {
        return context.resources.getStringArray(resId)
    }

    /**
     * 获取图片
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return 图片
     */
    fun getDrawable(context: Context, resId: Int): Drawable? {
        return context.resources.getDrawable(resId)
    }

    /**
     * 获取颜色
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return 颜色
     */
    fun getColor(context: Context, resId: Int): Int {
        return context.resources.getColor(resId)
    }

    /**
     * 根据id获取颜色的状态选择器
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return 状态选择器
     */
    fun getColorStateList(context: Context, resId: Int): ColorStateList? {
        return context.resources.getColorStateList(resId)
    }

    //获取像素值
    fun getDimen(context: Context, resId: Int): Int {
        return context.resources.getDimensionPixelSize(resId)
    }

    /**
     * 获取系统认为最小滑动距离TouchSlop,当小于该值时,忽略滑动(8dp)
     *
     * @param context 上下文对象
     */
    fun getTouchSlop(context: Context?): Int {
        //mSlop = ViewConfiguration.getWindowTouchSlop();
        return ViewConfiguration.get(context).scaledTouchSlop
    }

    /**
     * 加载布局文件
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return view
     */
    fun inflate(context: Context?, resId: Int): View? {
        return View.inflate(context, resId, null)
    }

    /**
     * 获取资源(例:获取drawable资源，name:drawable文件名称(shape_common),type(drawable))
     *
     * @param context 上下文对象
     * @param name 资源名称
     * @param type 资源类型
     * @return 资源
     */
    fun resources(context: Context, name: String?, type: String?): Int {
        return context.resources.getIdentifier(name, type, context.packageName)
    }

    /**
     * 对话框弹起时activity改变透明度
     *
     * @param activity activity对象
     * @param visible 透明度是否改变
     */
    fun setActivityPopupWindowBg(activity: Activity, visible: Boolean) {
        val params = activity.window.attributes
        if (visible) {
            params.alpha = 0.7f
        } else {
            params.alpha = 1.0f
        }
        activity.window.attributes = params
    }

    /**
     * 服务是否运行
     *
     * @param context 上下文对象
     * @param serviceName 服务名字
     * @return 是否开启 true-开启
     */
    fun isRunning(context: Context, serviceName: String): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = am.getRunningServices(20)
        for (info in services) {
            if (info.service.className == serviceName) {
                return true
            }
        }
        return false
    }
}