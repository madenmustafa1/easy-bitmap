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

internal fun Bitmap.centerCrop(scaleFactor: Double = 0.0): Bitmap? {
    try {
        var bitmap: Bitmap? = null
        if (this.width >= this.height) {
             bitmap =  Bitmap.createBitmap(
                this,
                this.width / 2 - this.height / 2,
                0,
                this.height,
                this.height
            )

        } else {
            bitmap = Bitmap.createBitmap(
                this,
                0,
                this.height / 2 - this.width / 2,
                this.width,
                this.width
            )
        }
        return if (scaleFactor > 0) bitmap.zoom()
        else bitmap
    } catch (e: Exception) {
        return null
    }
}

internal fun Bitmap.zoom(scaleFactor: Double = 0.75): Bitmap? {
    return try {
        val widthOffset = ((1 - scaleFactor) / 2 * this.width).toInt()
        val heightOffset = ((1 - scaleFactor) / 2 * this.height).toInt()
        val numWidthPixels: Int = this.width - 2 * widthOffset
        val numHeightPixels: Int = this.height - 2 * heightOffset
        Bitmap.createBitmap(
            this,
            widthOffset,
            heightOffset,
            numWidthPixels,
            numHeightPixels,
            null,
            true
        )
    } catch (e: Exception) {
        null
    }
}
