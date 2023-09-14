package ddwu.com.mobile.fooddbexam

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import ddwu.com.mobile.fooddbexam.databinding.ActivityUpdateBinding


class UpdateActivity : AppCompatActivity() {

    lateinit var updateBinding: ActivityUpdateBinding
    lateinit var helper: FoodDBHelper
    val TAG = "UpdateActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateBinding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(updateBinding.root)

        helper = FoodDBHelper(this)

        updateBinding.btnUpdateFood.setOnClickListener {
            val id = updateBinding.etUpdateId.text.toString() // 사용자가 입력한 ID를 가져옴
            val changename = updateBinding.etUpdateFood.text.toString()


            val db = helper.writableDatabase
            val updateRow = ContentValues()
            updateRow.put(FoodDBHelper.COL_FOOD, changename) // 컬럼 이름을 직접 사용
            val whereClause = "${BaseColumns._ID} = ?"
            val whereArgs = arrayOf(id)
            val updatedRows = db.update(FoodDBHelper.TABLE_NAME, updateRow, whereClause, whereArgs)

            helper.close()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        updateBinding.btnUpdateCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}


