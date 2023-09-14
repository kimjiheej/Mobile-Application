package ddwu.com.mobile.fooddbexam

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ddwu.com.mobile.fooddbexam.databinding.ActivityRemoveBinding

class RemoveActivity : AppCompatActivity() {

    lateinit var removeBinding: ActivityRemoveBinding
    lateinit var helper: FoodDBHelper

    val TAG = "MainActivity"

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeBinding = ActivityRemoveBinding.inflate(layoutInflater)
        setContentView(removeBinding.root)


        helper = FoodDBHelper(this)

        removeBinding.btnRemoveFood.setOnClickListener {
            val db = helper.writableDatabase
            val whereClause = "food=?"
            val whereArgs = arrayOf(removeBinding.etRemoveFood.text.toString())
            db.delete("${FoodDBHelper.TABLE_NAME}", whereClause, whereArgs)
            helper.close()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        removeBinding.btnRemoveCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        }
    }

