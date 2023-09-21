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

    @Query("UPDATE food_table SET country = :country WHERE food = :foodName")
    suspend fun updateFood(foodName : String, country : String)

    @Query("DELETE FROM food_table WHERE food =:foodName")
    suspend fun deleteFood(foodName : String)


    @Query("SELECT * FROM food_table WHERE food = :foodName")
    suspend fun getFood(foodName: String): Food
}

