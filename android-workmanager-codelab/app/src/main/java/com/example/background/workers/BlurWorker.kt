package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.KEY_IMAGE_URI

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    private val TAG by lazy { BlurWorker::class.java.simpleName }

    override fun doWork(): Result {
        val appContext = applicationContext
        val resourceURI = inputData.getString(KEY_IMAGE_URI)
        makeStatusNotification("Blurring Image", appContext)
        return try {
            if(TextUtils.isEmpty(resourceURI)){
                Log.e(TAG, "INVALID INPUT URI")
                throw IllegalArgumentException("Invalid Input URI")
            }
            val resolver = appContext.contentResolver
            val picture = BitmapFactory.decodeStream(resolver.openInputStream(Uri.parse(resourceURI)))
            val output = blurBitmap(picture, appContext)
            val outputUri = writeBitmapToFile(applicationContext, output)
            val outputData = Data.Builder().putString(KEY_IMAGE_URI, outputUri.toString()).build()

            Result.success(outputData)
        } catch (throwable: Throwable){
            Log.e(TAG, "Error applying blur", throwable)
            Result.failure()
        }
    }

}