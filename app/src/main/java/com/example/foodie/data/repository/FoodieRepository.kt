package com.example.foodie.data.repository

import com.example.foodie.data.api.FoodieApi
import com.example.foodie.data.api.RetrofitInstance

class FoodieRepository {

    // API instance'ını RetrofitInstance'tan alıyoruz
    private val api: FoodieApi = RetrofitInstance.api

    // Bu fonksiyon, API'den yemek araması yapacak
    // Cevap başarılı olsa bile, ViewModel iletmek için ham veriyi döndürüyoruz.
    suspend fun searchMeals(query: String) = api.searchMeals(query)

}