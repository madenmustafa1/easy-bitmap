package com.maden.easy_bitmap.utils.extensions

import android.graphics.Bitmap
import android.util.Base64
import com.maden.easy_bitmap.utils.exceptions.Assertion
import java.io.ByteArrayOutputStream


internal fun Bitmap.toByteArray(
    quality: Int = 100,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG
): ByteArray? {
    Assertion.quality(data = quality)
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(format, quality, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}

internal fun Bitmap.toBase64(
    quality: Int = 100,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG
): String {
    val byteArray = this.toByteArray(quality = quality, format = format)
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}
