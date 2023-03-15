package com.danny.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Handle基类
 *
 * @author danny
 * @since 2022/9/9
 */
public abstract class BaseHandle<T> extends Handler {
    private WeakReference<T> mWeakReference;

    public BaseHandle(T object) {
        mWeakReference = new WeakReference<>(object);
    }

    public BaseHandle(@NonNull Looper looper, T object) {
        super(looper);
        mWeakReference = new WeakReference<>(object);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Object obj = mWeakReference.get();
        if (!needHandleMessage(obj)) {
            removeCallbacksAndMessages((Object) null);
        } else {
            handleWeakReferenceNotNull(msg);
        }
    }

    private boolean needHandleMessage(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Activity) {
            Activity activity = (Activity) obj;
            if (activity.isFinishing() || activity.isDestroyed()) {
                return false;
            }
        }
        return !(obj instanceof Fragment) || ((Fragment) obj).isAdded();
    }

    protected abstract void handleWeakReferenceNotNull(Message msg);
}
