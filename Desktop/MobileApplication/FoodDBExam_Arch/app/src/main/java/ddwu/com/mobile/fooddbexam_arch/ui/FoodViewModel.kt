package ddwu.com.mobile.fooddbexam_arch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ddwu.com.mobile.fooddbexam_arch.data.Food
import ddwu.com.mobile.fooddbexam_arch.data.FoodRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FoodViewModel(var repository : FoodRepository) : ViewModel() {
    var allFoods : LiveData<List<Food>> = repository.allFoods.asLiveData() // 계속 감시해서 반영하기 위해서 만들어 놓은 아이다
   // 화면에 있는 데이터를 라이브 데이터를 활용해서 유지하게 해주는 것이다.


    // suspend 로 만들면 동시에 수행하고 싶은 함수들을 사용하는 것이다.
    // viewModel 전용의 scope 이다. 요 기능이 만들어지고 사용되는 그 시점에 적절하게 알아서 동작하게 된다.
    fun addFood(food : Food) = viewModelScope.launch {
        repository.addFood(food)
    }

    fun deleteFood(food : String) = viewModelScope.launch {
        repository.deleteFood(food)
    }

    fun updteFood(food: String, country : String) = viewModelScope.launch {
        repository.updateFood(food,country)
    }

    fun foodByCountry(country : String) = viewModelScope.launch {
        repository.foodByCountry(country)
    }
}

class FoodViewModelFactory(private val repository : FoodRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FoodViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return FoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}


