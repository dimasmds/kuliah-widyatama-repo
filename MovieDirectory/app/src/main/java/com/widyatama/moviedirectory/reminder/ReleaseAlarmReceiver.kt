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
import com.widyatama.moviedirectory.activity.DetailMovieActivity
import com.widyatama.moviedirectory.core.model.movie.Movie
import com.widyatama.moviedirectory.core.model.movie.Result
import com.widyatama.moviedirectory.core.presenter.MoviePresenter
import com.widyatama.moviedirectory.core.view.MovieView
import java.text.SimpleDateFormat
import java.util.*


class ReleaseAlarmReceiver : BroadcastReceiver(), MovieView {

    private var context: Context? = null

    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null

    override fun onReceive(context: Context, intent: Intent) {
        this.context = context
        Log.d(javaClass.simpleName, "On Receive Called")
        val presenter = MoviePresenter(this)
        presenter.getUpcomingMovies(1)
    }

    private fun showAlarmNotification(context: Context?, id: Int, title: String) {
        val notificationManager: NotificationManager?

        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.ID_MOVIE, id)
        intent.putExtra(DetailMovieActivity.TITLE_MOVIE, title)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification_white_48px)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_notification_white_48px))
                .setContentTitle("New Movie Release !")
                .setContentText("$title Now Release! Let's find out")
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(id, builder.build())
    }

    fun setAlarm(context: Context, message: String) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReleaseAlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 8)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        alarmManager!!.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY, pendingIntent)

        val alarmUp = PendingIntent.getBroadcast(context, 0,
                Intent(context, ReleaseAlarmReceiver::class.java),
                PendingIntent.FLAG_NO_CREATE) != null
        if (alarmUp) {
            Toast.makeText(context, "Release Notification is active", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Release Notification is deactivated", Toast.LENGTH_SHORT).show()
        }
    }

    fun cancelAlarm(context: Context) {
        if (alarmManager != null && pendingIntent != null) {
            alarmManager!!.cancel(pendingIntent)
            Toast.makeText(context, "Release Notification is deactivated", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMovieLoading() {}

    override fun hideMovieLoading() {}

    override fun showMovieError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMovies(data: List<Result>) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        val now = dateFormat.format(date)
        for (movie in data) {
            if (movie.releaseDate.equals(now)) {
                movie.id?.let { movie.title?.let { it1 -> showAlarmNotification(context, it, it1) } }
            }
        }
    }

    override fun showMovie(data: Movie) {}

    companion object {

        const val EXTRA_MESSAGE = "message"

        const val CHANNEL_ID = "channel_02"
        const val CHANNEL_NAME = "Movie Notification Release"
    }
}
