package com.maden.easy_bitmap_ai

import android.graphics.Bitmap
import com.maden.easy_bitmap_ai.classes.FaceDetection

class EasyBitmapAI {

    /**
     * @param bitmap -> input your bitmap faces
     * @param listener -> output faces list
     */

    suspend fun detectFaces(
        bitmap: Bitmap,
        listener: suspend (result: ArrayList<Bitmap>) -> Unit
    ) = FaceDetection.detectFaces(bitmap = bitmap, listener = listener)

    fun ocr(bitmap: Bitmap) = Unit

}