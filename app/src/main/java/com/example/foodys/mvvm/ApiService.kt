package com.example.foodys.mvvm

import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String
    ): Result

    /*
    @GET("recipes/findByNutrients")
    suspend fun getNutrientRecipes(
        @Query("apiKey") apiKey: String,
        @Query("minProtein") minProtein: Int = 10,
        @Query("maxProtein") maxProtein: Int = 50
    ): List<Recipe>

     */ //[2. Correct API Service Implementation
   // Your ApiService is correctly set up, but you may need to confirm the expected parameters for the findByNutrients endpoint. For instance, it likely requires more parameters than just the apiKey.]

    // ye shai na ho to bhi api me data fetch nhi hota hai
    // jese uper dekho isme jayag qurey thi to dalni padi nhi to data show nhi kar rha tha
    @GET("recipes/findByNutrients")
    suspend fun getNutrientRecipes(
        @Query("apiKey") apiKey: String,
        @Query("minCalories") minCalories: Int = 0,
        @Query("maxCalories") maxCalories: Int = 500,
        @Query("minProtein") minProtein: Int = 0,
        @Query("maxProtein") maxProtein: Int = 100
    ): List<Recipe>  // Adjust parameters as needed based on API docs

}
