package com.danny

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object XLog {
    private const val VERBOSE = 1
    private const val DEBUG = 2
    private const val INFO = 3
    private const val WARN = 4
    private const val ERROR = 5
    private var level = VERBOSE

    private val sdf =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun v(tag: String, msg: String) {
        if (level <= VERBOSE) {
            Log.v(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (level <= DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (level <= INFO) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (level <= WARN) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (level <= ERROR) {
            Log.e(tag, msg)
        }
    }

    fun logW() {
        val date = Date()
        val time = sdf.format(date)
    }

    fun logE() {
        val date = Date()
        val time: String = sdf.format(date)
    }

}