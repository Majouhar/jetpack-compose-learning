package com.majou.composelearning.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majou.model.MealsRepository
import com.majou.model.responses.MealResponse
import com.majou.model.responses.MealsCategoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoryViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
//    private val mealsJob = Job()

    init {
//        val scope = CoroutineScope(mealsJob + Dispatchers.IO)
        viewModelScope.launch {
            val meals = getMeals()
            mealState.value = meals
        }

    }

    val mealState: MutableState<List<MealResponse>> = mutableStateOf(emptyList())
    private suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals()
    }

//    override fun onCleared() {
//        super.onCleared()
//        mealsJob.cancel()
//    }
}