package com.example.foodie.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.foodie.R
import com.example.foodie.data.model.Meal
import com.example.foodie.util.Extensions.getParcelableExtraProvider

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val meal = intent.getParcelableExtraProvider<Meal>("MEAL_DATA")

        meal?.let {
            val image: ImageView = findViewById(R.id.detailImage)
            val title: TextView = findViewById(R.id.detailTitle)
            val instructionsText: TextView = findViewById(R.id.instructionsText)
            val btnYoutube: Button = findViewById(R.id.btnYoutube)

            title.text = it.name
            instructionsText.text = it.instructions
            image.load(it.imageUrl)

            btnYoutube.setOnClickListener { _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.youtubeUrl))
                startActivity(intent)
            }

        }
    }
}