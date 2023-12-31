package ddwu.com.mobile.fooddbexam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
// 각각 애노테이션 달아주어야 한다
interface FoodDao {

    @Query("SELECT * FROM food_table")
    fun getAllFoods() : List<Food>

    @Query("Select * FROM food_table WHERE country = :country")
    fun getFoodByCountry(country: String) : List<Food>

    @Insert
    fun insertFood(vararg food : Food)

    @Update
    fun updateFood(food : Food)

    @Delete
    fun deleteFood(food : Food)
}

