package com.example.foodie

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodie.ui.viewmodel.MealViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        observeMealsLiveData()

        viewModel.searchMeals("cake")
    }

    private fun observeMealsLiveData() {
        viewModel.mealResponse.observe(this) { response ->
            if (response.isSuccessful) {
                val meals = response.body()?.meals

                if (meals.isNullOrEmpty()) {
                    Log.d("Foodie", "Hiçbir yemek bulunamadı")
                } else {
                    Log.d("Foodie", "Başarılı ${meals.size} adet yemek bulundu")

                    meals.forEach { m ->
                        Log.d("Foodie", "Yemek adı ${m.name}, ID: ${m.id}")
                    }
                }
            } else {
                Log.e("Foodie", "API isteği başarısız oldu: ${response.code()}")
            }
        }
    }
}