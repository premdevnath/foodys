package com.example.foodys.Ui2.screen


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.foodys.R
import com.example.foodys.mvvm.viewmodel.RecipeViewModel
import kotlinx.coroutines.delay
import com.example.foodys.mvvm.ResultX
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel


//koil

@Composable
fun homescreen(navController: NavController) {
    val viewModel: RecipeViewModel = koinViewModel() // Koin ViewModel
    val resultXList by viewModel.resultXList
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage by viewModel.errorMessage
    // Simulate loading with delay
    LaunchedEffect(Unit) {
        delay(2000) // Simulate network delay
        viewModel.fetchResultX()
        isLoading.value = false // Set loading to false after delay
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        // Display error message if exists
        errorMessage?.let {
            Text(text = it, color = Color.Red, fontWeight = FontWeight.Bold)
        }
        // Popular Recipes Label
        Text(
            text = "Popular Recipes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Horizontal List of Popular Recipes
        if (isLoading.value) {
            LazyRow {
                items(5) { // Show 5 skeleton cards
                    SkeletonCard()
                }
            }
        } else {
            LazyRow {
                items(resultXList) { recipe ->
                    RecipeCard(recipe, navController)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // All Recipes Label
        Text(
            text = "All Recipes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Vertical List of All Recipes
        if (isLoading.value) {
            LazyColumn {
                items(5) { // Show 5 skeleton cards
                    SkeletonCard()
                }
            }
        } else {
            LazyColumn {
                items(resultXList) { recipe ->
                    RecipeCard(recipe, navController)
                }
            }
        }
    }
}


@Composable
fun RecipeCard(recipe: ResultX, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .clickable {
                val recipeJson = Uri.encode(Gson().toJson(recipe))
                navController.navigate("detailsScreen/$recipeJson")
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = recipe.image,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SkeletonCard() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.3f))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Skeleton image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Gray.copy(alpha = 0.3f), Color.LightGray)
                        )
                    )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Skeleton text placeholder (Recipe Name)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray.copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Skeleton text placeholder (Details)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
        }
    }
}


