package com.example.foodys.di
import android.app.Application

import com.example.foodys.mvvm.appModule
import com.example.foodys.mvvm.viewmodel.RecipeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule) // Load Koin modules
        }
    }
}