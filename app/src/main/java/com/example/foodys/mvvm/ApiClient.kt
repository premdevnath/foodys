package com.example.foodys.mvvm

import com.example.foodys.mvvm.repo.RecipeRepository
import com.example.foodys.mvvm.viewmodel.RecipeViewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


//koil (di use)
// Koin Module
val appModule = module {
    single { provideRetrofit() }               // Retrofit instance
    single { provideApiService(get()) }        // ApiService instance
    single { RecipeRepository(get()) }         // Repository instance
    viewModel { RecipeViewModel(get()) }       // ViewModel instance
}

// Provide Retrofit
fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

// Provide ApiService
fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
