package com.maden.easy_bitmap

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.activity.viewModels
import java.io.File

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmap = viewModel.tempBitmap(this) ?: return

        val file = File(
            this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                .toString() + "/bitmap_bitmap_${System.currentTimeMillis()}.png"
        )

        viewModel.bitmapSaveFile(file = file, bitmap = bitmap)
        viewModel.bitmapSaveFromFileName(bitmap = bitmap, context = this)

        val fileToBitmap = viewModel.fileToBitmap(file = file)
        val fileNameToBitmap = viewModel.fileNameToBitmap(fileName = file.absolutePath)
        val base64 = viewModel.bitmapToBase64(bitmap = bitmap)
        val byteArray = viewModel.bitmapToByteArray(bitmap = bitmap)
        val base64ToBitmap = viewModel.base64ToBitmap(base64 = base64)
        val bitmapChangeType = viewModel.bitmapChangeType(bitmap = bitmap, format = Bitmap.CompressFormat.JPEG)

        val centerCrop = viewModel.bitmapCenterCrop(bitmap = bitmap)
        val bitmapZoom = viewModel.bitmapZoom(bitmap = bitmap, scaleFactor = .1)

        this.findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmapZoom)
    }

}