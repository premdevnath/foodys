package com.example.foodys.Ui2.screen

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodys.mvvm.ResultX
import com.example.foodys.mvvm.viewmodel.RecipeViewModel
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items

import com.example.foodys.mvvm.Recipe
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel


@Composable
fun home2screen(navController: NavController) {
    val viewModel: RecipeViewModel = koinViewModel()
    val recipeList by viewModel.recipeList
    Log.d("hi1", "${recipeList}")
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage by viewModel.errorMessage // To capture error message

    // Simulate network delay for fetching data
    LaunchedEffect(Unit) {
        try {
            delay(2000) // Simulate network delay
            viewModel.fetchRecipe() // Fetch data
            isLoading.value = false // Set loading to false after delay
        } catch (e: Exception) {
            // Handle the error and set the error message
           // viewModel.setErrorMessage("Failed to load recipes. Please try again.")
            isLoading.value = false
        }
    }
    /*
    // Simulate network delay for fetching data
    LaunchedEffect(Unit) {
        delay(2000) // Simulate network delay
        viewModel.fetchRecipe()
        isLoading.value = false // Set loading to false after delay
    }

     */

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Nutritional Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))
        // Show error message if there's an error
        errorMessage?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodyMedium)
            return@Column
        }
        // Show loading skeleton while fetching data
        if (isLoading.value) {
            LazyColumn {
                items(5) { // Show 5 skeleton cards
                    SkeletonCard3()
                }
            }
        }

        else {
            // Show the actual list of recipes once data is fetched
            LazyColumn {
                items(recipeList) { nutrient ->
                    NutrientItem(nutrient, navController)

                }
            }
        }
    }
}

@Composable
fun NutrientItem(nutrient: Recipe, navController: NavController) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                val recipeJson = Uri.encode(Gson().toJson(nutrient))
                navController.navigate("detailsScreen/$recipeJson")
            }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = nutrient.image,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.2f))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = nutrient.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Calories: ${nutrient.calories}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Protein: ${nutrient.protein}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Fat: ${nutrient.fat}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Carbs: ${nutrient.carbs}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun SkeletonCard3() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp)
    ) {
        // Image Placeholder
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.3f))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            // Title Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Description Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
        }
    }
}
