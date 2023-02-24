package com.maden.easy_bitmap_ai.classes

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

internal class Ocr {

    private val recognition by lazy { TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) }

    fun ocrBitmap(bitmap: Bitmap, listener: (text: String) -> Unit) {
        val inputImage = InputImage.fromBitmap(bitmap, 0)
        recognition.process(inputImage).addOnSuccessListener { text ->
            val result = StringBuilder()
            for (block in text.textBlocks) {
                for (line in block.lines) {
                    for (element in line.elements) {
                        val elementText = element.text
                        result.append(elementText)
                    }
                }
            }
            listener.invoke(result.toString())
        }
    }

}