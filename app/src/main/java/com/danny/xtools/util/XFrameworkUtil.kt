package com.danny.xtools.util

import android.app.Activity
import android.graphics.Rect
import android.view.WindowInsets

/**
 * FrameWork层util
 *
 * @author danny
 * @since 2020-11-27
 */
object XFrameworkUtil {
    private const val LAYOUT_EX = "com.huawei.android.view.WindowManagerEx\$LayoutParamsEx"
    private const val REGION_EX = "com.huawei.android.view.DisplaySideRegionEx"

    fun getScreenType(activity: Activity, callback: ScreenTypeCallback) {
        val normal = 1 // 正常平
        val digging = 2 // 挖孔屏
        val ring = 3 // 曲面屏
        activity.window.decorView
            .setOnApplyWindowInsetsListener { v, insets ->
                try {
                    val layoutClass = Class.forName(LAYOUT_EX)
                    val displayClass = Class.forName(REGION_EX)
                    val declaredConstructor =
                        ReflectUtil.getReflectConstructor(
                            layoutClass,
                            activity.window.attributes
                        )
                    if (null != declaredConstructor) {
                        // getDisplaySideRegion
                        val getDisplaySideRegion = layoutClass
                            .getDeclaredMethod("getDisplaySideRegion", WindowInsets::class.java)
                        val sideRegion =
                            getDisplaySideRegion.invoke(declaredConstructor, insets)
                        if (null == sideRegion) {
                            callback.screenType(normal)
                        } else {
                            val getSafeInsets =
                                displayClass.getMethod("getSafeInsets")
                            val safeInset =
                                getSafeInsets.invoke(sideRegion) as Rect
                            if (safeInset.left > 0 && safeInset.right > 0) { // 曲面屏
                                callback.screenType(ring)
                            } else if (safeInset.top > 0) { // 挖孔屏
                                callback.screenType(digging)
                            }
                        }
                    }
                } catch (e: Throwable) {
                    callback.screenType(normal)
                }
                v.onApplyWindowInsets(insets)
            }
    }

    interface ScreenTypeCallback {
        fun screenType(screenType: Int)
    }

}