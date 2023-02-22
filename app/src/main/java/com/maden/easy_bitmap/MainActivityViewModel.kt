package com.maden.easy_bitmap

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.maden.easy_bitmap.classes.EasyBitmap
import java.io.File

class MainActivityViewModel : ViewModel() {

    private val easyBitmap = EasyBitmap()

    fun tempBitmap(context: Context): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, R.drawable.monkey) ?: return null
        return easyBitmap.drawableToBitmap(drawable)
    }

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

    /**
     * from bitmap
     */
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

    /**
     * to Bitmap
     */

    fun base64ToBitmap(base64: String) = easyBitmap.base64ToBitmap(base64)

    fun bitmapChangeType(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100,
    ) = easyBitmap.bitmapChangeType(bitmap = bitmap, format = format, quality = quality)

}