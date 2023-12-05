package com.example.geekyhub.newsapplication.retrofit

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)