package com.majou.model

import com.majou.model.api.MealsWebService
import com.majou.model.responses.MealResponse

class MealsRepository(private  val webService: MealsWebService = MealsWebService()) {
    private var cachedMeals = listOf<MealResponse>()
    suspend fun getMeals():List<MealResponse> {
        cachedMeals = webService.getMeals().categories
        return cachedMeals
    }

    fun getMeal(id:String):MealResponse?{
        return cachedMeals.firstOrNull{
            it.id == id
        }
    }
    companion object{
        @Volatile
        private var instance:MealsRepository? = null
        fun getInstance() = instance?: synchronized(this){
            instance?:MealsRepository().also{ instance = it}
        }
    }
}