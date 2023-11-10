package ddwu.com.mobile.alarmtest

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyBroadcastReceiver : BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val msg = intent?.getStringExtra("MSG")
        Log.d("MYBROADCASTrECEIVER","RECEIVED : ${msg}")

        val intent = Intent(context,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val pIntent = PendingIntent.getActivity(context,0,intent,PendingIntent)

        val channelId = context?.resources?.getString(R.string.channel_id)

       val builder = NotificationCompat.Builder(context!!,channelId!!)
           .setSmallIcon(R.drawable.ic_launcher_foreground)
           .setContentTitle("수업 끝!")
           .setContentText("점심 먹을 시간!")
           .setPriority(NotificationCompat.PRIORITY_DEFAULT)
           .setContentIntent(pIntent)
           .setAutoCancel(true)

        val notiManager = NotificationManagerCompat.from(context)
        notiManager.notify(100,builder.build())
    }
}