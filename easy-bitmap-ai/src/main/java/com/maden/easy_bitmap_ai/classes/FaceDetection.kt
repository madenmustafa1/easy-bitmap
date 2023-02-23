package com.maden.easy_bitmap_ai.classes

import android.graphics.*
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.maden.easy_bitmap.EasyBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


internal object FaceDetection {

    private val faceDetectionOptions = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
        .setContourMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
        .build()

    private val easyBitmap = EasyBitmap()

    suspend fun detectFaces(bitmap: Bitmap, listener: suspend (result: ArrayList<Bitmap>) -> Unit) {
        val faceDetector = FaceDetection.getClient(faceDetectionOptions)
        val image = InputImage.fromBitmap(bitmap, 0)
        faceDetector.process(image).addOnSuccessListener { images ->
            if (!images.isNullOrEmpty()) {
                val faceList = arrayListOf<Bitmap>()
                images.forEach {
                    val face = easyBitmap.rectCropBitmap(
                        bitmap = bitmap,
                        rect = it.boundingBox
                    )
                    face?.let { faceList.add(face) }
                }
                CoroutineScope(Dispatchers.IO).launch {
                    listener.invoke(faceList)
                }
            }
        }
    }
}