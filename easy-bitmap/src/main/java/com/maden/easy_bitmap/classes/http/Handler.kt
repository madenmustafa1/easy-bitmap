package com.maden.easy_bitmap.classes.http

import android.os.Handler
import android.os.Looper

internal fun Any.mainThread(
    thread: () -> Unit
) {
    Handler(Looper.getMainLooper())
        .post { thread() }
}