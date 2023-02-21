package com.maden.easy_bitmap.utils.extensions

import android.graphics.Bitmap
import android.util.Base64
import com.maden.easy_bitmap.utils.exceptions.Assertion
import java.io.File


internal fun String.toFile() = File(this)

internal fun String.toBitmap(): Bitmap? {
    Assertion.string(data = this)
    return Base64.decode(this, Base64.DEFAULT).toBitmap()
}