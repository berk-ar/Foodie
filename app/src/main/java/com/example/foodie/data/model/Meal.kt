package com.example.foodie.data.model

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal")
    val id: String,

    @SerializedName("strMeal")
    val name: String,

    @SerializedName("strInstructions")
    val instructions: String,

    @SerializedName("strMealThumb")
    val imageUrl: String,

    @SerializedName("strYoutube")
    val youtubeUrl: String
)
