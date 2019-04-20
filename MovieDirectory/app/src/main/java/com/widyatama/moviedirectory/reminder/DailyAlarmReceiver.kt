package com.widyatama.moviedirectory.reminder

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.activity.MainActivity
import java.util.*


class DailyAlarmReceiver : BroadcastReceiver() {

    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(javaClass.simpleName, "On Receive Called")
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val title = context.resources.getString(R.string.app_name)
        showAlarmNotification(context, title, message)
    }

    private fun showAlarmNotification(context: Context, title: String, message: String) {
        val notificationManager: NotificationManager?

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification_white_48px)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_notification_white_48px))
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }


    fun setAlarm(context: Context, message: String) {

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyAlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        alarmManager!!.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY, pendingIntent)

        val alarmUp = PendingIntent.getBroadcast(context, 0,
                Intent(context, DailyAlarmReceiver::class.java),
                PendingIntent.FLAG_NO_CREATE) != null
        if (alarmUp) {
            Toast.makeText(context, "Daily Notification is active", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Daily Notification is deactivated", Toast.LENGTH_SHORT).show()
        }
    }

    fun cancelAlarm(context: Context) {

        if (alarmManager != null && pendingIntent != null) {
            alarmManager!!.cancel(pendingIntent)
            Toast.makeText(context, "Daily Notification is deactivated", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        const val EXTRA_MESSAGE = "message"
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_01"
        const val CHANNEL_NAME = "Movie Notification"
    }

}