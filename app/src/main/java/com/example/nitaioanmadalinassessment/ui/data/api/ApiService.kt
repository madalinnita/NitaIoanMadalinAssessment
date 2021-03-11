package com.example.nitaioanmadalinassessment.ui.data.api

import com.example.nitaioanmadalinassessment.ui.data.models.articles.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getAllArticles(@Query("q") category: String): ArticlesResponse
}