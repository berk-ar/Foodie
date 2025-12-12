package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.model.MealResponse
import com.example.foodie.data.repository.FoodieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MealViewModel : ViewModel() {
    private val repository = FoodieRepository()

    val mealResponse: MutableLiveData<Response<MealResponse>> = MutableLiveData()

    fun searchMeals(query: String) {
        // Coroutine başlatıyoruz işlem ana thread'i bloke etmesin diye
        viewModelScope.launch {
            try {
                // Repository veriyi çekiyoruz
                val response = repository.searchMeals(query)
                // LiveData güncelliyoruz
                mealResponse.postValue(response)
            } catch (e: Exception) {
                println("API hatası ${e.message}")
            }
        }
    }
}