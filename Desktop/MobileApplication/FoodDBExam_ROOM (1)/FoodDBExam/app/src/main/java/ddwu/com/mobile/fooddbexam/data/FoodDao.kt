package ddwu.com.mobile.fooddbexam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface FoodDao {

    @Query("select * from food_table")
    fun getAllFoods() : List<Food>

    @Query("SELECT * FROM food_table WHERE country = :country")
    fun getFoodByCountry(country: String) : List<Food>

    @Insert
    fun insertFood(vararg food : Food)

    @Update
    fun updateFood(food : Food)

    @Delete
    fun deleteFood(food : Food)
}

