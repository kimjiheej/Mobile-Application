package ddwu.com.mobile.notitest


import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ddwu.com.mobile.notitest.databinding.ActivityMainBinding
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        createNotificationChannel()

        mainBinding.btnNoti.setOnClickListener {
            Thread {
                sleep(3000)
     //           showNotification()
                Intent().also{
                    it.setAction("ACTION_SNOOZE")
                    it.putExtra("NOTI_ID",1000)
                    sendBroadcast(intent)
                }
            }.start()
        }

        mainBinding.btnNotiAction.setOnClickListener {
            Thread {
                sleep(3000)
                showNotificationWithAction()
            }.start()
        }
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Notification Channel 의 생성
            val channelID = resources.getString(R.string.channel_id)
            val name = "Test Channel"
            val descriptionText = "Test Channel Message"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(channelID, name, importance)
            mChannel.description = descriptionText

            // Channel 을 시스템에 등록, 등록 후에는 중요도 변경 불가
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
            Toast.makeText(applicationContext, "${notificationManager.areNotificationsEnabled()}", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotification() {
        val intent = Intent(this,AlertBroadcastReceiver::class.java)
//            .apply
//        {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//        }

        val pIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE)

        val channelId = resources.getString(R.string.channel_id)

        val builder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.ic_action_name)
            .setContentTitle("알림 제목")
            .setContentText("짧은 알림 내용")
            .setStyle(NotificationCompat.BigTextStyle().bigText("확장 시 확인할 수 있는 긴 알림 내용"))
            .setContentIntent(pIntent)
            .setAutoCancel(true)

        val notiManager = NotificationManagerCompat.from(this)
        notiManager.notify(100,builder.build())
    }

    @SuppressLint("MissingPermission")
    private fun showNotificationWithAction() { // 알림을 사용해서 다른 action 을 사용할 수 있는 것
        val intent = Intent(this,AlertActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val pIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE)

        val channelId = resources.getString(R.string.channel_id)

        val builder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.ic_action_name)
            .setContentTitle("알림 제목")
            .setContentText("짧은 알림 내용")
            .setStyle(NotificationCompat.BigTextStyle().bigText("확장 시 확인할 수 있는 긴 알림 내용"))
            .setContentIntent(pIntent)
            .setAutoCancel(true)
            .addAction(R.drawable.ic_action_name,"쉬기",pIntent)

        val notiManager = NotificationManagerCompat.from(this)
        notiManager.notify(100,builder.build())
    }

}