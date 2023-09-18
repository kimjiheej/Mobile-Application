package ddwu.com.mobile.fooddbexam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

interface FoodDao {
    fun getAllFoods() : List<Food>

    fun getFoodByCountry(country: String) : List<Food>

    fun insertFood(vararg food : Food)

    fun updateFood(food : Food)

    fun deleteFood(food : Food)
}

