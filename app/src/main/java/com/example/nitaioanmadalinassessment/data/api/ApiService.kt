package com.example.nitaioanmadalinassessment.data.api

import UnrdResponse
import com.example.nitaioanmadalinassessment.data.models.articles.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getAllArticles(@Query("q") category: String): ArticlesResponse

    @GET("resp.json")
    suspend fun getUnrdItems(): UnrdResponse
}