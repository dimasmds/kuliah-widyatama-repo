package com.riotfallen.periodicworkrequestexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import java.sql.Time
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG by lazy { MainActivity::class.java.simpleName }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraintWorker = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<MyPeriodicWork>(14, TimeUnit.HOURS)
            .setConstraints(constraintWorker)
            .build()

        WorkManager.getInstance().enqueue(workRequest)

    }
}
