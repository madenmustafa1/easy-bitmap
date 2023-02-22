package com.maden.easy_bitmap.utils.extensions

import android.media.Image
import com.maden.easy_bitmap.utils.exceptions.Assertion
import java.nio.ByteBuffer

fun Image.toByteArray(): ByteArray? {
    Assertion.imageFormat(image = this)
    try {
        val width = this.width
        val height = this.height
        val yPlane = this.planes[0]
        val uPlane = this.planes[1]
        val vPlane = this.planes[2]
        val yBuffer: ByteBuffer = yPlane.buffer
        val uBuffer: ByteBuffer = uPlane.buffer
        val vBuffer: ByteBuffer = vPlane.buffer

        val numPixels = (width * height * 1.5f).toInt()
        val nv21 = ByteArray(numPixels)
        var index = 0

        val yRowStride = yPlane.rowStride
        val yPixelStride = yPlane.pixelStride
        for (y in 0 until height) {
            for (x in 0 until width) {
                nv21[index++] = yBuffer.get(y * yRowStride + x * yPixelStride)
            }
        }

        val uvRowStride = uPlane.rowStride
        val uvPixelStride = uPlane.pixelStride
        val uvWidth = width / 2
        val uvHeight = height / 2
        for (y in 0 until uvHeight) {
            for (x in 0 until uvWidth) {
                val bufferIndex = y * uvRowStride + x * uvPixelStride
                nv21[index++] = vBuffer.get(bufferIndex)
                nv21[index++] = uBuffer.get(bufferIndex)
            }
        }
        return nv21
    } catch (e: Exception) {
        return null
    }
}