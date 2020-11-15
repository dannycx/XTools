package com.danny.xtools.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

/**
 * 是否支持Android11.+
 *
 * @author danny
 * @since 2020-11-14
 */
public class OSUtil {
    public static final String TAG = "OSVersionUtil";
    private Object LOCK_OBJECT = new Object();
    private OSEntity ROM_ENTITY = new OSEntity();

    public static boolean isSupportSystem() {
        OSEntity entity = getConfig();
        String emui = entity.getEmui();
        String emuiVersion = TextUtils.isEmpty(emui) ? "" : emui.toUpperCase();
        if (!emuiVersion.startsWith("EMUI") && !emuiVersion.startsWith("MAGICUI")) {
            return false;
        } else {
            int version = getMaxVersion(emuiVersion);
            return version >= 11 || version > 4;
        }
    }

    private static int getMaxVersion(String emuiVersion) {
        emuiVersion = emuiVersion.replaceAll("EMUI", "").replace("MAGICUI", "");
        String[] v = emuiVersion.split("\\.");
        return NumberUtil.INSTANCE.toInt(v[0]);
    }

    private OSEntity getConfig() {
        if (ROM_ENTITY.isValid()) {
            return ROM_ENTITY;
        } else {
            synchronized (LOCK_OBJECT) {
                String jsonStr = initConfig();
                ROM_ENTITY.convert(jsonStr);
            }
            return ROM_ENTITY;
        }
    }

    private String initConfig() {
        return null;
    }


    public static class OSEntity {
        @SerializedName("emui_version")
        private String emui;

        public boolean isValid() {
            return !XStringUtil.INSTANCE.isBlank(emui);
        }

        public void convert(String jsonStr) {
            if (XStringUtil.INSTANCE.isBlank(jsonStr)) {
                return;
            }
            try {
                OSEntity entity = new Gson().fromJson(jsonStr, OSEntity.class);
                this.emui = entity.emui;
            } catch (JsonSyntaxException e) {
                Log.e(TAG, e.toString());
            }
        }

        public String getEmui() {
            return emui;
        }
    }
}
