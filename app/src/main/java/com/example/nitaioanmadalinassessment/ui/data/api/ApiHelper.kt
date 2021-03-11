package com.example.nitaioanmadalinassessment.ui.data.api

import com.example.nitaioanmadalinassessment.ui.data.models.articles.ArticlesResponse

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllArticles(): ArticlesResponse = apiService.getAllArticles("bitcoin")
}