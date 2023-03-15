package com.danny.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View

/**
 *
 *
 * @author danny
 * @since 2021/7/22
 */
object XBitmapUtil {


    fun viewToBitmap(view : View?) : Bitmap? {
        var bitmap: Bitmap? = null
        if (view == null) {
            return bitmap
        }
        view.isDrawingCacheEnabled = true
        if (view.measuredHeight == 0) {
            return bitmap
        }
        for (i in (0 until 2)) {
            if (bitmap != null) {
                continue
            }
            bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        }
        if (bitmap == null) {
            return bitmap
        }
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun getBitmapFromResources(context: Context, resId: Int): Bitmap
        = BitmapFactory.decodeResource(context.resources, resId)
}