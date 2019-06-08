package com.gorrotowi.drxstore.utils

import android.util.Log

fun Any.loge(message: String, exception: Exception? = null) {
    exception?.let {
        Log.e(this::class.java.simpleName, message, it)
    } ?: also {
        Log.e(this::class.java.simpleName, message)
    }
}