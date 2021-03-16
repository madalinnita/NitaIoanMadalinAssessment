package com.example.nitaioanmadalinassessment.data.api

import UnrdResponse
import com.example.nitaioanmadalinassessment.data.models.articles.ArticlesResponse

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllArticles(category: String): ArticlesResponse = apiService.getAllArticles(category)
    suspend fun getUnrdItems(): UnrdResponse = apiService.getUnrdItems()
}