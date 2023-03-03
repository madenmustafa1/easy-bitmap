package com.maden.easy_bitmap.classes.worker

/*
import android.content.Context
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.maden.easy_bitmap.classes.http.DownloadService
import java.util.concurrent.CountDownLatch

@RequiresApi(api = 26)
internal class DownloadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return downloadImage()
    }

    private suspend fun downloadImage(): Result {
        try {
            val latch = CountDownLatch(1)
            val url = inputData.getString("url") ?: run {
                return Result.failure()
            }
            val filePath = inputData.getString("filePath") ?: run {
                return Result.failure()
            }

            var result = Result.retry()

            DownloadService.downloadImage(
                url = url,
                exception = {
                    result = Result.failure()
                    latch.countDown()
                }
            ) {
                it?.let {
                    result = Result.success()
                    latch.countDown()
                } ?: run {
                    result = Result.retry()
                    latch.countDown()
                }
            }

            latch.await()

            return result
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}

 */