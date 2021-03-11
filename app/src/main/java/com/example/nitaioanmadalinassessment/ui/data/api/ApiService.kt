package com.example.nitaioanmadalinassessment.ui.data.api

import com.example.nitaioanmadalinassessment.ui.data.models.articles.ArticlesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("everything")
    suspend fun getAllArticles(): List<ArticlesResponse>
}