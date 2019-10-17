package com.banyan.algorithm.utils

import android.util.Log

object LogUtil {
    private val TAG = "LogUtil"

    private val V = Log.isLoggable(TAG, Log.VERBOSE)
    private val I = Log.isLoggable(TAG, Log.INFO)
    private val D = Log.isLoggable(TAG, Log.DEBUG)
    private val W = Log.isLoggable(TAG, Log.WARN)
    private val E = Log.isLoggable(TAG, Log.ERROR)

    fun i(tag: String, msg: String) {
        if (I) {
            Log.i(TAG, "[$tag]:$msg")
        }
    }

    fun v(tag: String, msg: String) {
        if (V) {
            Log.v(TAG, "[$tag]:$msg")
        }
    }

    fun d(tag: String, msg: String) {
        if (D) {
            Log.d(TAG, "[$tag]:$msg")
        }
    }

    fun w(tag: String, msg: String) {
        if (W) {
            Log.w(TAG, "[$tag]:$msg")
        }
    }

    fun e(tag: String, msg: String, e: Throwable) {
        if (E) {
            Log.e(TAG, "[$tag]:$msg", e)
        }
    }


}