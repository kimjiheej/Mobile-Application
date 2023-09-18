package ddwu.com.mobile.fooddbexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ddwu.com.mobile.fooddbexam.data.Food
import ddwu.com.mobile.fooddbexam.data.FoodDao
import ddwu.com.mobile.fooddbexam.data.FoodDatabase
import ddwu.com.mobile.fooddbexam.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var binding : ActivityMainBinding

//    lateinit var db : FoodDatabase
//    lateinit var foodDao : FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        db = /*FoodDatabase 생성*/
//        foodDao = db.foodDao()

        showAllFoods()

        binding.btnSelect.setOnClickListener{
            showFoodByCountry("한국")
        }

        binding.btnAdd.setOnClickListener{
            addFood( Food(0, "된장찌개", "한국") )
        }

        binding.btnUpdate.setOnClickListener{
            modifyFood( Food(1, "김치찌개", "한국") )
        }

        binding.btnRemove.setOnClickListener{
            removeFood( Food(2, null, null) )
        }

    }


    /*각 함수 내부에서 적절한 DAO 호출*/

    fun addFood(food: Food) {

    }

    fun modifyFood(food: Food) {

    }

    fun removeFood(food: Food) {

    }

    fun showFoodByCountry(country: String) {

    }

    fun showAllFoods() {

    }
}