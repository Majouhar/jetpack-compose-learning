package com.majou.composelearning.ui.details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.majou.model.MealsRepository
import com.majou.model.responses.MealResponse

class MealDetailsViewModel(savedStateHandle:SavedStateHandle):ViewModel() {
    private val repository: MealsRepository = MealsRepository.getInstance()
    val mealState = mutableStateOf<MealResponse?>(null)
    init{
        val mealId = savedStateHandle.get<String>("id")?:""
        Log.e("TESTA",mealId)
        mealState.value  = repository.getMeal(mealId)
    }
}