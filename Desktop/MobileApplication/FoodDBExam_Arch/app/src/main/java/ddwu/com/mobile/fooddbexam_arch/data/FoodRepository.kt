package ddwu.com.mobile.fooddbexam_arch.data

import kotlinx.coroutines.flow.Flow


class FoodRepository(private val foodDao : FoodDao) {

    val allFoods : Flow<List<Food>> = foodDao.getAllFoods()

    suspend fun addFood(food : Food){ // 직접 접근하는게 아니라 모아서 하는것이다
        foodDao.insertFood(food)
    }

    suspend fun updateFood(food : String, country : String){
        foodDao.updateFood(food,country)
    }

    suspend fun deleteFood(food : String){
        foodDao.deleteFood(food)
    }

    suspend fun foodByCountry (country : String){
        foodDao.getFoodByCountry(country)
    }
}