package com.maden.easy_bitmap.utils.extensions

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView

internal fun ImageView.toBitmap(): Bitmap? {
    this.drawable?.let {
        return (it as? BitmapDrawable)?.bitmap
    } ?: run {
        return null
    }
}