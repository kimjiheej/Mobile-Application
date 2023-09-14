package ddwu.com.mobile.fooddbexam

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpdate.setOnClickListener{

            val intent = Intent(this,UpdateActivity::class.java)
            startActivity(intent)
        }

        binding.btnRemove.setOnClickListener{
              val intent = Intent(this,RemoveActivity::class.java)
            startActivity(intent)
        }
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