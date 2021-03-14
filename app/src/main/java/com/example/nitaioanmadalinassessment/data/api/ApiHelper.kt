package com.example.nitaioanmadalinassessment.data.api

import com.example.nitaioanmadalinassessment.data.models.articles.ArticlesResponse

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllArticles(category: String): ArticlesResponse = apiService.getAllArticles(category)
}