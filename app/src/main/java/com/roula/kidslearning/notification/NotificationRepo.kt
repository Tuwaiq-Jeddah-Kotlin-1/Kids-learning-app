package com.roula.kidslearning.notification

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.roula.kidslearning.MainActivity
import java.util.concurrent.TimeUnit

class NotificationRepo {
        fun myNotification(mainActivity: MainActivity){
            val myWorkRequest= PeriodicWorkRequest
                .Builder(WorkerNotification::class.java,7, TimeUnit.DAYS)
                .setInputData(workDataOf(
                    "title" to "We miss you kids \uD83D\uDC6B",
                    "message" to "Come back and learn more \uD83D\uDC96")
                )
                .build()
            WorkManager.getInstance(mainActivity).enqueueUniquePeriodicWork(
                "periodicStockWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                myWorkRequest
            )
        }
    }
