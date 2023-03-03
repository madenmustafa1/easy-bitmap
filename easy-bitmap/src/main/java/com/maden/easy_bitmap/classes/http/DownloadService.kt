package com.maden.easy_bitmap.classes.http

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.maden.easy_bitmap.utils.extensions.initHttpRequest
import okhttp3.*
import java.io.IOException

internal object DownloadService {

    private val client by lazy { OkHttpClient() }

    fun downloadImage(
        url: String,
        exception: (exception: Exception) -> Unit = { },
        headers: Map<String, String>? = null,
        bitmap: (bitmap: Bitmap?) -> Unit
    ) {
        try {
            client.newCall(url.initHttpRequest(headers)).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    mainThread { exception(e) }
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val result = BitmapFactory.decodeStream(response.body?.byteStream())
                        mainThread { bitmap(result) }
                    } else {
                        val reason = Exception(
                            "Error: Message -> ${response.message}, Status Code -> ${response.code}"
                        )
                        mainThread { exception(reason) }
                    }
                }
            })
        } catch (e: Exception) {
            mainThread { exception(e) }
        }
    }
/*
    @RequiresApi(api = 26)
    fun downloadImageWithWorker(url: String, filePath: String, context: Context): Operation {
        Assertion.string(url)
        Assertion.string(filePath)

        val data = Data.Builder()
        data.putString("url", url)
        data.putString("filePath", filePath)
        val request: WorkRequest =
            OneTimeWorkRequestBuilder<DownloadWorker>()
                .setInputData(data.build())
                .build()

        return WorkManager.getInstance(context).enqueue(request)
    }
 */
}