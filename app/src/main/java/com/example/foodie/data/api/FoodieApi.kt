package com.example.foodie.data.api

import com.example.foodie.data.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodieApi {

    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): Response<MealResponse>
}