package com.example.foodys.mvvm.repo


import android.util.Log
import com.example.foodys.mvvm.ApiService
import com.example.foodys.mvvm.Recipe
import com.example.foodys.mvvm.ResultX

class RecipeRepository(private val apiService: ApiService) {

    suspend fun getRecipes(apiKey: String): List<ResultX> {
        return try {
            apiService.getRecipes(apiKey).results
        } catch (e: Exception) {
            Log.e("RecipeRepository", "Error fetching recipes: ${e.message}")
            emptyList()
        }
    }

    suspend fun getNutrientRecipes(apiKey: String): List<Recipe> {
        return try {
            apiService.getNutrientRecipes(apiKey)
        } catch (e: Exception) {
            Log.e("RecipeRepository", "Error fetching nutrient recipes: ${e.message}")
            emptyList()
        }
    }
}


//koil without error handling
/*
class RecipeRepository (
    private val apiService: ApiService
) {
    suspend fun getRecipes(apiKey: String): List<ResultX> {
        return try {
            apiService.getRecipes(apiKey).results
        } catch (e: Exception) {
            emptyList()
        }
    }
    suspend fun getNutrientRecipes(apiKey: String): List<Recipe> =
        try {
            apiService.getNutrientRecipes(apiKey)
        } catch (e: Exception) { emptyList() }
}


 */

