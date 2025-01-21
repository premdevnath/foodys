plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("kapt")
    id("kotlin-kapt")

}

android {
    namespace = "com.example.foodys"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.foodys"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "mozilla/public-suffix.list.txt"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //ss
    implementation("androidx.core:core-splashscreen:1.0.0")
    //navigate
    implementation ("androidx.navigation:navigation-compose:2.8.4")
    // Jetpack Compose
    implementation ("androidx.compose.ui:ui:1.0.5")
    implementation ("androidx.compose.material:material:1.0.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.5")
    implementation ("androidx.navigation:navigation-compose:2.4.0-alpha10")
    implementation ("androidx.compose.material3:material3:1.1.1")
    implementation ("androidx.compose.runtime:runtime:1.5.1")
    implementation ("androidx.compose.foundation:foundation:1.5.1")
    implementation("androidx.compose.material:material-icons-extended-android:1.5.0")

    // Retrofit and RxKotlin
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.2")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")

    // Koin for Dependency Injection

    implementation("io.insert-koin:koin-android:4.0.0")
    implementation("io.insert-koin:koin-androidx-compose:4.0.0")
    implementation("io.insert-koin:koin-androidx-navigation:4.0.0")





    // Shimmer Effect
    implementation ("com.valentinilk.shimmer:compose-shimmer:1.0.0")
    // Image loading (Coil for Jetpack Compose)
    implementation ("io.coil-kt:coil-compose:2.4.0")
    //
    // Shimmer for loading animation
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    //skeleton loding / shimmer effect
    implementation ("androidx.compose.foundation:foundation:1.5.1")

// Jetpack Compose lifecycle and LiveData integration
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")

// ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

// LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    //viewpagger

    implementation ("com.google.accompanist:accompanist-pager:0.30.1")  // For ViewPager support
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.1")  // For Pager Indicators (optional)
    implementation ("androidx.activity:activity-compose:1.6.0")  // Activity compose integration
}