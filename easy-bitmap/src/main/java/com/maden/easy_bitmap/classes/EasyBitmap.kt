package com.maden.easy_bitmap.classes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.maden.easy_bitmap.utils.extensions.*
import com.maden.easy_bitmap.utils.extensions.toBase64
import com.maden.easy_bitmap.utils.extensions.toBitmap
import com.maden.easy_bitmap.utils.extensions.toFile
import com.maden.easy_bitmap.utils.extensions.writeBitmap
import java.io.File

class EasyBitmap {

    /**
     * @sample file;
     * @param file = "Environment.DIRECTORY_PICTURES/easy-bitmap.png"
     * @param file = "easy-bitmap-file/easy-bitmap.jpg"
     * @param file = "easy-bitmap-file/easy-bitmap.jpeg"
     *
     * @sample quality;
     * @param quality 1 and 100 must be between.
     */
    fun saveBitmapFromFile(
        bitmap: Bitmap,
        file: File,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100
    ) = file.writeBitmap(bitmap = bitmap, format = format, quality = quality)

    /**
     * @sample fileName;
     * @param fileName = "easy-bitmap.png"
     * @param fileName = "easy-bitmap.jpg"
     * @param fileName = "easy-bitmap.jpeg"
     *
     * @sample quality;
     * @param quality 1 and 100 must be between.
     *
     * @see Bitmap location -> app/DIRECTORY_PICTURES/fileName
     */
    fun saveBitmapFromName(
        bitmap: Bitmap,
        fileName: String,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100,
        context: Context
    ) = context.writeBitmap(
        fileName = fileName,
        bitmap = bitmap,
        format = format,
        quality = quality
    )

    /**
     * from Bitmap
     */
    /**
     * @sample file -> File("path")
     */
    fun fileToBitmap(file: File) = file.toBitmap()

    /**
     * @sample fileName -> "../../easy-bitmap.png
     * @sample fileName -> "../../easy-bitmap.jpeg
     * @sample fileName -> "../../easy-bitmap.jpg
     */
    fun fileNameToBitmap(fileName: String) = fileName.toFile().toBitmap()

    /**
     * @sample quality;
     * @param quality 1 and 100 must be between.
     *
     * @see -> It's recommended to use the async function for large photos.
     * Like coroutine: developer.android.com/kotlin/coroutines
     */
    fun bitmapToBase64(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100,
    ) = bitmap.toBase64(format = format, quality = quality)

    /**
     * @sample quality;
     * @param quality 1 and 100 must be between.
     *
     * @see -> It's recommended to use the async function for large photos.
     * Like coroutine: developer.android.com/kotlin/coroutines
     */
    fun bitmapToByteArray(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        quality: Int = 100,
    ) = bitmap.toByteArray(format = format, quality = quality)

    /**
     * @sample drawable -> ContextCompat.getDrawable(this, R.drawable.monkey)
     */
    fun drawableToBitmap(drawable: Drawable) = drawable.toBitmap()


    /**
     * to Bitmap
     */

    /**
     * @sample base64 = bitmapToBase64(bitmap)
     *
     * @see -> It's recommended to use the async function for large photos.
     * Like coroutine: developer.android.com/kotlin/coroutines
     */
    fun base64ToBitmap(base64: String) = base64.toBitmap()

}