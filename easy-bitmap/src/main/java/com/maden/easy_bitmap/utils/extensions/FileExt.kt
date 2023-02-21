package com.maden.easy_bitmap.utils.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.maden.easy_bitmap.utils.exceptions.Assertion
import java.io.File


internal fun File.writeBitmap(
    bitmap: Bitmap,
    format: Bitmap.CompressFormat,
    quality: Int
) {
    Assertion.quality(data = quality)
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
}

internal fun File.toBitmap(): Bitmap? {
    if (!this.exists()) return null
    val filePath = this.path
    return BitmapFactory.decodeFile(filePath)
}