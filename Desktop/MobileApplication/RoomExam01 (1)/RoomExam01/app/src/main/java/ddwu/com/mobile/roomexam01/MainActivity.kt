package ddwu.com.mobile.roomexam01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import ddwu.com.mobile.roomexam01.data.Food
import ddwu.com.mobile.roomexam01.data.FoodDao
import ddwu.com.mobile.roomexam01.data.FoodDatabase
import ddwu.com.mobile.roomexam01.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding
    //lateinit var foodAdapter: FoodAdapter

    lateinit var db : FoodDatabase
    lateinit var foodDao : FoodDao

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext, FoodDatabase::class.java, "food_db"
        ).build()
        foodDao = db.foodDao()


       CoroutineScope(Dispatchers.IO).launch {
           foodDao.insertFood(Food(null, "된장찌개", "한국"))
           foodDao.insertFood(Food(null, "김치찌개", "한국"))
           foodDao.insertFood(Food(null, "마라탕", "중국"))
           foodDao.insertFood(Food(null, "훠궈", "중국"))
           foodDao.insertFood(Food(null, "스시", "일본"))
           foodDao.insertFood(Food(null, "오코노미야키", "일본"))

           showAllFoods()
       }



//        /*RecyclerView 의 layoutManager 지정*/
//        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this).apply {
//            orientation = LinearLayoutManager.VERTICAL
//        }



       // foodAdapter = FoodAdapter(foodDao.getAllFoods())

        /*foodAdapter 에 LongClickListener 구현 및 설정*/
//        val onLongClickListener = object: FoodAdapter.OnItemLongClickListener {
//            override fun onItemLongClickListener(view: View, pos: Int) {
//                Log.d(TAG, "Long Click!! $pos")
//            }
//        }
      //  foodAdapter.setOnItemLongClickListener(onLongClickListener)

      //  binding.foodRecyclerView.adapter = foodAdapter


        binding.btnShow.setOnClickListener{
            val country = binding.etNation.text.toString()
            showCountryFood(country)
        }

        binding.btnInsert.setOnClickListener{
            val foodName = binding.etFood.text.toString()
            val countryName = binding.etNation.text.toString()
            val newFood = Food(null,foodName,countryName)
            addFood(newFood)
        }
        binding.btnUpdate.setOnClickListener {

            val foodName = binding.etFood.text.toString()
            val countryName = binding.etNation.text.toString()
            modifyFood(foodName,countryName)
        }

        binding.btnDelete.setOnClickListener {

            val foodName = binding.etFood.text.toString()
            removeFood(foodName)
        }

    }

    /*각 함수 내부에서 적절한 DAO 호출*/
    fun addFood(food : Food) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.insertFood(food)
        }
    }

    fun modifyFood(foodName : String, countryName : String ) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.updateFood(foodName,countryName)
        }
    }

    fun removeFood(foodName : String) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.deleteFood(foodName)
        }
    }

    fun showCountryFood(country: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val foods = foodDao.getFoodByCountry(country)
            for (food in foods) {
                Log.d(TAG, food.toString())
            }
        }
    }

    fun showAllFoods() {
        CoroutineScope(Dispatchers.IO).launch {
            val flowFoods = foodDao.getAllFoods()
            flowFoods.collect { foods ->
                for (food in foods) {
                    Log.d(TAG, food.toString())
                }
            }
        }
    }
}