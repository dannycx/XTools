package com.danny.xtools.util;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * 系统工具类
 *
 * @author danny
 * @since 2018/10/11.
 */
public class XSystemUtil {
    private long startTime = 0;

    private XSystemUtil() {}

    private static class SingleInstance {
        private static XSystemUtil INSTANCE = new XSystemUtil();
    }

    public static XSystemUtil getInstance() {
        return SingleInstance.INSTANCE;
    }

    /**
     * 双击退出应用
     *
     * @param context 上下文
     */
    public void doubleExit(Context context) {
        if (System.currentTimeMillis() - startTime < 1000) {// 双击
            Process.killProcess(0);
            System.exit(0);
        }else {
            startTime = System.currentTimeMillis();
            Toast.makeText(context, "再点击一次退出应用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 多击事件
     */
    long[] hits = new long[3];// 数字为几,就是几击事件
    public void multipleClick() {
        System.arraycopy(hits, 1, hits, 0, hits.length - 1);
        hits[hits.length - 1] = SystemClock.uptimeMillis();
        if (hits[hits.length - 1] - hits[0] < 1000) {// 几击事件
            // todo:处理多击事件
        }
    }

    /**
     * 开机启动动画,AnimationDrawable动画图片资源回收
     *
     * @param drawable 动画drawable
     */
    public void recycleAnimationDrawable(AnimationDrawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.stop();
        for (int i = 0; i < drawable.getNumberOfFrames(); i++) {
            Drawable frame = drawable.getFrame(i);
            if (frame instanceof BitmapDrawable) {
                ((BitmapDrawable)frame).getBitmap().recycle();
            }
            frame.setCallback(null);
        }
        drawable.setCallback(null);
    }

}
