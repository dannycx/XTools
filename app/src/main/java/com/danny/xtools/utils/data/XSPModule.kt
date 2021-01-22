package com.danny.xtools.utils.data

import android.content.SharedPreferences

/**
 * sp工具
 *
 * @author danny
 * @since 2020-12-8
 */
open class XSPModule {
    protected lateinit var sp: SharedPreferences

    fun put(key: String, value: String, commit: Boolean = false) {
        when(commit) {
            true -> sp.edit().putString(key, value).commit()
            else -> sp.edit().putString(key, value).apply()
        }
    }

    fun put(key: String, value: Boolean, commit: Boolean = false) {
        when(commit) {
            true -> sp.edit().putBoolean(key, value).commit()
            else -> sp.edit().putBoolean(key, value).apply()
        }
    }

    fun put(key: String, value: Float, commit: Boolean = false) {
        when(commit) {
            true -> sp.edit().putFloat(key, value).commit()
            else -> sp.edit().putFloat(key, value).apply()
        }
    }

    fun put(key: String, value: Int, commit: Boolean = false) {
        when(commit) {
            true -> sp.edit().putInt(key, value).commit()
            else -> sp.edit().putInt(key, value).apply()
        }
    }

    fun put(key: String, value: Long, commit: Boolean = false) {
        when(commit) {
            true -> sp.edit().putLong(key, value).commit()
            else -> sp.edit().putLong(key, value).apply()
        }
    }

    fun getString(key: String, default: String = "") = sp.getString(key, default) ?: ""

    fun getInt(key: String, default: Int = 0) = sp.getInt(key, default)

    fun getLong(key: String, default: Long = 0) = sp.getLong(key, default)

    fun getBoolean(key: String, default: Boolean = false) = sp.getBoolean(key, default)

    fun getFloat(key: String, default: Float = 0f) = sp.getFloat(key, default)

    fun remove(key: String, commit: Boolean = false) {
        when(commit) {
            true -> sp.edit().remove(key).commit()
            else -> sp.edit().remove(key).apply()
        }
    }

    fun contains(key: String) = sp.contains(key)

    fun clear(commit: Boolean = false) {
        when(commit) {
            true -> sp.edit().clear().commit()
            else -> sp.edit().clear().apply()
        }
    }
}
