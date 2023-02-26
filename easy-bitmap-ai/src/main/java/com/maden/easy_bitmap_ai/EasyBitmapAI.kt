package com.maden.easy_bitmap_ai

import android.graphics.Bitmap
import com.maden.easy_bitmap_ai.classes.FaceDetection
import com.maden.easy_bitmap_ai.classes.Ocr

class EasyBitmapAI() {

    private val faceDetection by lazy { FaceDetection() }
    private val ocr by lazy { Ocr() }

    /**
     * @param bitmap -> input bitmap faces
     * @param listener -> output faces list
     */

    suspend fun detectFaces(
        bitmap: Bitmap,
        listener: suspend (result: ArrayList<Bitmap>) -> Unit
    ) = faceDetection.detectFaces(
        bitmap = bitmap,
        listener = listener
    )

    /**
     * @param bitmap -> input bitmap text
     * @param listener -> output text
     * @throws -> It will not work on devices without google play services.
     */
    fun ocr(
        bitmap: Bitmap,
        listener: (text: String) -> Unit
    ) = ocr.ocrBitmap(
        bitmap = bitmap,
        listener = listener
    )

}