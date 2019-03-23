package com.riotfallen.periodicworkrequestexample

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyPeriodicWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val TAG by lazy { MyPeriodicWork::class.java.simpleName }

    override fun doWork(): Result {
        Log.e(TAG, "doWork: Work is done")

        makeNotification("Worker anda berjalan !", applicationContext)

        return Result.success()
    }

}