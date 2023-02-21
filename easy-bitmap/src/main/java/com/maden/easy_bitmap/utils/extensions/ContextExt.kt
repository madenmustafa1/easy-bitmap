package com.maden.easy_bitmap.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File

internal fun Context.writeBitmap(
    bitmap: Bitmap,
    fileName: String,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 100,
): File {
    val file = File(
        this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            .toString() + "/" + fileName
    )
    file.writeBitmap(bitmap = bitmap, format = format, quality = quality)
    return file
}