package com.example.nitaioanmadalinassessment.data.api

import com.example.nitaioanmadalinassessment.data.models.articles.ArticlesResponse

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllArticles(): ArticlesResponse = apiService.getAllArticles("bitcoin")
}