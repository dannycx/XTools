package com.danny.xtools.utils.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HistoryUtil {

    private const val HISTORY_FILE = "history_file"

    fun get(key: String): MutableList<String> {
        val history = XSP.getInstance(HISTORY_FILE).getString(key)
        if (history.isNotBlank()) {
            return Gson().fromJson(history, object : TypeToken<MutableList<String>>() {}.type)
        }
        return emptyList<String>().toMutableList()
    }

    fun save(key: String, value: String) {
        val history = get(key)
        if (value !in history) {
            history.add(0, value)
        } else {
            history.remove(value)
            history.add(0, value)
        }
        XSP.getInstance(HISTORY_FILE).put(key, value)
    }

    fun clear(key: String) {
        when (contains(key)) {
            true -> XSP.getInstance(HISTORY_FILE).clear()
        }
    }

    fun contains(key: String) = XSP.getInstance(HISTORY_FILE).contains(key)
}
