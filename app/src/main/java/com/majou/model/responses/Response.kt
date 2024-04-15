package com.majou.model.responses

import com.google.gson.annotations.SerializedName

data class MealsCategoryResponse( val categories:List<MealResponse>)
data class MealResponse(
    @SerializedName("idCategory")   val id:String,
    @SerializedName("strCategory")  val name:String,
    @SerializedName("strCategoryDescription")  val description:String,
    @SerializedName("strCategoryThumb")  val imageURL:String
)