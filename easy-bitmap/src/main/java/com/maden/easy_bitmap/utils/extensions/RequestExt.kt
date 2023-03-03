package com.maden.easy_bitmap.utils.extensions

import com.maden.easy_bitmap.utils.exceptions.Assertion
import okhttp3.Request

fun String.initHttpRequest(
    headers: Map<String, String>? = null
): Request {
    Assertion.string(this)
    return Request
        .Builder()
        .url(this)
        .apply {
            headers?.let {
                for (i in it) addHeader(i.key, i.value)
            }
        }.build()
}