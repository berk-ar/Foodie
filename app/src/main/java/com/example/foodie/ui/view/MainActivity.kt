package com.example.foodie.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.ui.adapter.MealAdapter
import com.example.foodie.ui.viewmodel.MealViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MealViewModel by viewModels()
    private lateinit var mealAdapter: MealAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv)
        progressBar = findViewById(R.id.progressBar)

        // rv kurulumu
        setupRecyclerView()

        // veriyi gözlemler
        observeMealsLiveData()

        viewModel.searchMeals("cake")
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        mealAdapter = MealAdapter()
        recyclerView.adapter = mealAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mealAdapter.onMealClick = { meal ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("MEAL_DATA", meal)
            startActivity(intent)
        }
    }

    private fun observeMealsLiveData() {
        viewModel.mealResponse.observe(this) { response ->
            progressBar.visibility = View.GONE
            if (response.isSuccessful) {
                val meals = response.body()?.meals
                if (meals != null) {
                    mealAdapter.setMeals(meals)
                } else {
                    Log.d("Foodie", "Liste boş geldi")
                }
            } else {
                Log.e("Foodie", "Hata: ${response.code()}")
            }
        }
    }
}