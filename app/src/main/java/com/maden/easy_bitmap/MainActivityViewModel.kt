package com.maden.easy_bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.media.Image
import android.os.Environment
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.maden.easy_bitmap_ai.EasyBitmapAI
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import java.io.File

class MainActivityViewModel : ViewModel() {

    private val easyBitmap = EasyBitmap()
    private val easyBitmapAI = EasyBitmapAI()

    fun tempBitmap(context: Context, @DrawableRes draw: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, draw) ?: return null
        return easyBitmap.drawableToBitmap(drawable)
    }

    fun tempFile(context: Context) = File(
        context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            .toString() + "/bitmap_bitmap_${System.currentTimeMillis()}.png"
    )

    fun bitmapSaveFile(bitmap: Bitmap, file: File) {
        easyBitmap.saveBitmapFromFile(
            bitmap = bitmap,
            file = file,
            format = Bitmap.CompressFormat.JPEG,
            quality = 100
        )
    }

    fun bitmapSaveFromFileName(bitmap: Bitmap, context: Context) {
        easyBitmap.saveBitmapFromName(
            fileName = "easy-bitmap_bitmapSaveFromFileName_${System.currentTimeMillis()}.png",
            bitmap = bitmap,
            format = Bitmap.CompressFormat.JPEG,
            quality = 100,
            context = context
        )
    }

    fun fileToBitmap(file: File) = easyBitmap.fileToBitmap(file)

    fun fileNameToBitmap(fileName: String) = easyBitmap.fileNameToBitmap(fileName)

    fun bitmapToBase64(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100,
    ) = easyBitmap.bitmapToBase64(bitmap = bitmap, format = format, quality = quality)

    fun bitmapToByteArray(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100,
    ) = easyBitmap.bitmapToByteArray(bitmap = bitmap, format = format, quality = quality)

    fun base64ToBitmap(base64: String) = easyBitmap.base64ToBitmap(base64)

    fun bitmapChangeType(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100,
    ) = easyBitmap.bitmapChangeType(bitmap = bitmap, format = format, quality = quality)

    fun imageToByteArray(image: Image) = easyBitmap.imageToByteArray(image = image)

    fun imageViewToBitmap(imageView: ImageView) =
        easyBitmap.imageViewToBitmap(imageView = imageView)

    fun bitmapCenterCrop(
        bitmap: Bitmap,
        scaleFactor: Double = 0.0
    ) = easyBitmap.bitmapCenterCrop(bitmap = bitmap, scaleFactor = scaleFactor)

    fun bitmapZoom(
        bitmap: Bitmap,
        scaleFactor: Double = 0.75
    ) = easyBitmap.bitmapZoom(bitmap = bitmap, scaleFactor = scaleFactor)

    fun rectCenterCrop(bitmap: Bitmap, rect: Rect) =
        easyBitmap.rectCropBitmap(bitmap = bitmap, rect = rect)

    fun downloadImage(
        url: String,
        exception: (exception: Exception) -> Unit = { },
        headers: Map<String, String>? = null,
        bitmap: (bitmap: Bitmap?) -> Unit
    ) = easyBitmap.downloadImage(
        url = url,
        exception = exception,
        bitmap = bitmap,
        headers = headers
    )

    fun detectFaces(bitmap: Bitmap) = channelFlow {
        easyBitmapAI.detectFaces(bitmap = bitmap) {
            launch {
                send(it)
                close()
            }
        }
        awaitClose()
    }

    fun ocrBitmap(
        bitmap: Bitmap,
        listener: (text: String) -> Unit
    ) = easyBitmapAI.ocr(bitmap = bitmap, listener = listener)

}
