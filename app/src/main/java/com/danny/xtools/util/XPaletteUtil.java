package com.danny.xtools.util;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import android.view.View;

/**
 * 颜色拾取器
 *
 * @author danny
 * @since 2020-11-08
 */
public class XPaletteUtil {

    /**
     * 拾取图片颜色设置到指定控件
     *
     * @param bitmap 图片
     * @param view 目标view
     */
    public static void palette(Bitmap bitmap, final View view) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                view.setBackgroundDrawable(new ColorDrawable(swatch.getRgb()));
            }
        });
    }

}
