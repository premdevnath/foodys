package com.example.foodys.mvvm.viewmodel


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodys.mvvm.ResultX
import com.example.foodys.mvvm.repo.RecipeRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.example.foodys.mvvm.Recipe



class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _resultXList = mutableStateOf<List<ResultX>>(emptyList())
    val resultXList: State<List<ResultX>> = _resultXList

    private val _recipeList = mutableStateOf<List<Recipe>>(emptyList())
    val recipeList: State<List<Recipe>> = _recipeList

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun fetchResultX() {
        viewModelScope.launch {
            try {
                val recipes = repository.getRecipes("9d2a5c411ee04c05aa9f39ce3c2d2efb")
                _resultXList.value = recipes
            } catch (e: Exception) {
                _resultXList.value = emptyList()
                _errorMessage.value = "Error fetching popular recipes: ${e.message}"
            }
        }
    }

    fun fetchRecipe() {
        viewModelScope.launch {
            try {
                val nutrients = repository.getNutrientRecipes("9d2a5c411ee04c05aa9f39ce3c2d2efb")
                _recipeList.value = nutrients
            } catch (e: Exception) {
                _recipeList.value = emptyList()
                _errorMessage.value = "Error fetching nutrient recipes: ${e.message}"
            }
        }
    }
}

//koil without error handling
/*
class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _resultXList = mutableStateOf<List<ResultX>>(emptyList())
    val resultXList: State<List<ResultX>> = _resultXList

    private val _recipeList = mutableStateOf<List<Recipe>>(emptyList())
    val recipeList: State<List<Recipe>> = _recipeList

    fun fetchResultX() {
        viewModelScope.launch {
            try {
                val recipes = repository.getRecipes("9d2a5c411ee04c05aa9f39ce3c2d2efb")
                _resultXList.value = recipes
            } catch (e: Exception) {
                _resultXList.value = emptyList()
            }
        }
    }

    fun fetchRecipe() {
        viewModelScope.launch {
            try {
                val nutrients = repository.getNutrientRecipes("9d2a5c411ee04c05aa9f39ce3c2d2efb")
                _recipeList.value = nutrients
            } catch (e: Exception) {
                _recipeList.value = emptyList()
            }
        }
    }
}



 */