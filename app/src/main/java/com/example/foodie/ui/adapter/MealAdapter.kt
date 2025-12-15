package com.example.foodie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodie.R
import com.example.foodie.data.model.Meal

class MealAdapter : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private var mealList = emptyList<Meal>()

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealImage: ImageView = itemView.findViewById(R.id.mealImage)
        val mealName: TextView = itemView.findViewById(R.id.mealName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealAdapter.MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealAdapter.MealViewHolder, position: Int) {
        val currentMeal = mealList[position]

        holder.mealName.text = currentMeal.name

        holder.mealImage.load(currentMeal.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    fun setMeals(meals: List<Meal>) {
        this.mealList = meals
        notifyDataSetChanged() // listeyi yeniler
    }
}