package com.maden.easy_bitmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
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

        //to Bitmap
        val fileToBitmap = viewModel.fileToBitmap(file = file)
        val fileNameToBitmap = viewModel.fileNameToBitmap(fileName = file.absolutePath)

        //from bitmap
        val base64 = viewModel.bitmapToBase64(bitmap = bitmap)
        val byteArray = viewModel.bitmapToByteArray(bitmap = bitmap)
    }

}