package ddwu.com.mobile.fooddbexam

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import ddwu.com.mobile.fooddbexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var binding : ActivityMainBinding

    lateinit var helper : FoodDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        helper = FoodDBHelper(this)

        binding.btnSelect.setOnClickListener{
             showFoods()
        }

        binding.btnAdd.setOnClickListener{
            addFood()
        }

        binding.btnUpdate.setOnClickListener{
             modifyFood()
        }

        binding.btnRemove.setOnClickListener{
            deleteFood()
        }

    }

    fun addFood() {
            //insert
        val db = helper.writableDatabase
        val newRow = ContentValues()
        newRow.put("${FoodDBHelper.COL_FOOD}","햄버거")
        newRow.put("${FoodDBHelper.COL_COUNTRY}","미국")
        db.insert("${FoodDBHelper.TABLE_NAME}",null,newRow)
        helper.close()

    }

    fun modifyFood() {
        val db = helper.writableDatabase
        val updateRow = ContentValues()
        updateRow.put("${FoodDBHelper.COL_FOOD}","스파게티")
        updateRow.put("${FoodDBHelper.COL_COUNTRY}","이탈리아")
        val whereClause = "food=?"
        val whereArgs = arrayOf("불고기")
        db.update("${FoodDBHelper.TABLE_NAME}",updateRow,whereClause,whereArgs)
        helper.close()
    }

    fun deleteFood() {
           val db = helper.writableDatabase
           val whereClause = "${BaseColumns._ID} = ?"
           val whereArgs = arrayOf("1")
        db.delete("${FoodDBHelper.TABLE_NAME}",whereClause,whereArgs)
        helper.close()
    }

    @SuppressLint("Range")
    fun showFoods() { // select

        val list = ArrayList<FoodDto>()
        val db = helper.readableDatabase
        val columns = null
        val selection = null
        val selectionArgs = null
        val cursor = db.query(
            FoodDBHelper.TABLE_NAME,columns,selection,selectionArgs,null,null,null,null
        )

        with(cursor){
            while(moveToNext()){
                val id = getInt(getColumnIndex("_id"))
                val food = getString(getColumnIndex("food"))
                val country = getString(getColumnIndex("country"))
                val dto = FoodDto(id,food,country)
                list.add(dto) // 리스트에 데이터 추가
                Log.d(TAG,"$id - $food ($country)")
            }
        }

        cursor.close()
        helper.close()
        var data = ""
        for (dto in list){
            data += dto.toString()+"\n"
        }

        binding.tvDisplay.text = data;
    }
}