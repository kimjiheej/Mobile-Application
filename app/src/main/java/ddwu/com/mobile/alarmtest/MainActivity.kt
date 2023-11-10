package ddwu.com.mobile.alarmtest

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import ddwu.com.mobile.alarmtest.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val alarmManager by lazy{
        getSystemService(ALARM_SERVICE) as? AlarmManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        mainBinding.btnOneShot.setOnClickListener {

            val intent = Intent(this,MyBroadcastReceiver::class.java)
            intent.putExtra("MSG","MY MESSAGE")
            val alarmIntent = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_IMMUTABLE)

            alarmManager?.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 10 * 1000,
                alarmIntent
            )
        }

        mainBinding.btnRepeat.setOnClickListener {

        }

        mainBinding.btnStopAlarm.setOnClickListener {

        }

    }
}