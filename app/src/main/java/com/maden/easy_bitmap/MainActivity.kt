package com.maden.easy_bitmap

import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmap = viewModel.tempBitmap(this, R.drawable.monkey) ?: return
        val textBitmap = viewModel.tempBitmap(this, R.drawable.text) ?: return
        val file = viewModel.tempFile(this)

        viewModel.bitmapSaveFile(file = file, bitmap = bitmap)
        viewModel.bitmapSaveFromFileName(bitmap = bitmap, context = this)
        val fileToBitmap = viewModel.fileToBitmap(file = file)
        val fileNameToBitmap = viewModel.fileNameToBitmap(fileName = file.absolutePath)
        val base64 = viewModel.bitmapToBase64(bitmap = bitmap)
        val byteArray = viewModel.bitmapToByteArray(bitmap = bitmap)
        val base64ToBitmap = viewModel.base64ToBitmap(base64 = base64)
        val bitmapChangeType =
            viewModel.bitmapChangeType(bitmap = bitmap, format = Bitmap.CompressFormat.JPEG)
        val bitmapCenterCrop = viewModel.bitmapCenterCrop(bitmap = bitmap)
        val bitmapZoom = viewModel.bitmapZoom(bitmap = bitmap, scaleFactor = .1)
        val rectCropBitmap =
            viewModel.rectCenterCrop(bitmap = bitmap, rect = Rect(300, 300, 800, 800))

        viewModel.downloadImage(url = "URL") {
        }

        lifecycleScope.launch {
            viewModel.detectFaces(bitmap = bitmap).collect {
            }

            viewModel.ocrBitmap(textBitmap) {
            }
        }
    }
}