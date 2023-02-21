package com.maden.easy_bitmap.utils.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.maden.easy_bitmap.utils.exceptions.Assertion

internal fun ByteArray.toBitmap(): Bitmap? {
    Assertion.byteArray(data = this)
    return BitmapFactory.decodeByteArray(this, 0, this.size)
}