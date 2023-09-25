package ddwu.com.mobile.fooddbexam_arch


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.fooddbexam_arch.data.Food
import ddwu.com.mobile.fooddbexam_arch.databinding.ActivityMainBinding
import ddwu.com.mobile.fooddbexam_arch.ui.FoodViewModel
import ddwu.com.mobile.fooddbexam_arch.ui.FoodViewModelFactory

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    lateinit var mainBinding : ActivityMainBinding
    lateinit var adapter : FoodAdapter

    val viewModel : FoodViewModel by viewModels{
        FoodViewModelFactory((application as FoodApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        Log.d(TAG, "onCreate!!!")

        adapter = FoodAdapter()
        mainBinding.rvFoods.adapter = adapter
        mainBinding.rvFoods.layoutManager = LinearLayoutManager(this)

        viewModel.allFoods.observe(this,{
            adapter.foods = it
            adapter.notifyDataSetChanged()
        })

        mainBinding.btnAdd.setOnClickListener {
            val food = mainBinding.etFood.text.toString()
            val country = mainBinding.etCountry.text.toString()
            viewModel.addFood(Food(null,food,country))
        }

        mainBinding.btnModify.setOnClickListener {
            val food = mainBinding.etFood.text.toString()
            val country = mainBinding.etCountry.text.toString()
            viewModel.updteFood(food,country)
        }
        mainBinding.btnRemove.setOnClickListener {
            val food = mainBinding.etFood.text.toString()
            viewModel.deleteFood(food)
        }
        mainBinding.btnShow.setOnClickListener {
           val country = mainBinding.etCountry.text.toString()
            viewModel.foodByCountry(country)
        }
    }
}