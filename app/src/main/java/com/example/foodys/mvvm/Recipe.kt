package com.example.foodys.mvvm

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String,
    val calories: Int,
    val protein: String,
    val fat: String,
    val carbs: String
)