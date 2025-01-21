package com.example.foodys.mvvm

data class Result(
    val number: Int,
    val offset: Int,
    val results: List<ResultX>,
    val totalResults: Int
)