package com.maden.easy_bitmap.utils.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.maden.easy_bitmap.utils.exceptions.Assertion
import java.io.ByteArrayOutputStream


internal fun Bitmap.toByteArray(
    quality: Int = 100,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG
): ByteArray? {
    Assertion.quality(data = quality)
    return try {
        val byteArrayOutputStream = ByteArrayOutputStream()
        this.compress(format, quality, byteArrayOutputStream)
        byteArrayOutputStream.toByteArray()
    } catch (e: Exception) {
        null
    }
}

internal fun Bitmap.toBase64(
    quality: Int = 100,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG
): String {
    val byteArray = this.toByteArray(quality = quality, format = format)
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

internal fun Bitmap.changeType(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 100
): Bitmap? {
    Assertion.quality(data = quality)
    return try {
        val stream = ByteArrayOutputStream()
        this.compress(format, quality, stream)
        val byteArray: ByteArray = stream.toByteArray()
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    } catch (e: Exception) {
        null
    }
}
