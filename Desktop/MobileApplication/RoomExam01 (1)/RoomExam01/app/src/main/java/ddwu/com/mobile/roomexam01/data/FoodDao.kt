package ddwu.com.mobile.roomexam01.data

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
    fun getAllFoods() : Flow<List<Food>>

    @Query("SELECT * FROM food_table WHERE country = :country")
    suspend fun getFoodByCountry(country: String) : List<Food>

    @Insert
    suspend fun insertFood(vararg food : Food)

    @Update
    suspend fun updateFood(food : Food)

    @Delete
    suspend fun deleteFood(food : Food)


    @Query("SELECT * FROM food_table WHERE food = :foodName")
    suspend fun getFood(foodName: String): Food
}

