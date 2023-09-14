package ddwu.com.mobile.fooddbexam

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ddwu.com.mobile.fooddbexam.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var addBinding : ActivityAddBinding

    lateinit var helper : FoodDBHelper

    val TAG = "MainActivity"
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    addBinding = ActivityAddBinding.inflate(layoutInflater)
    setContentView(addBinding.root)

    helper = FoodDBHelper(this)

    addBinding.btnAddFood.setOnClickListener {
        // 사용자가 입력한 음식 이름과 국가를 읽어옴
        val food = addBinding.etAddFood.text.toString()
        val country = addBinding.etAddNation.text.toString()

            val db = helper.writableDatabase
            val newRow = ContentValues()
            newRow.put(FoodDBHelper.COL_FOOD, food)
            newRow.put(FoodDBHelper.COL_COUNTRY, country)
            val id = db.insert(FoodDBHelper.TABLE_NAME, null, newRow)
            Log.d(TAG, "new id: $id")
            helper.close()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }

    addBinding.btnAddCancel.setOnClickListener{

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
}