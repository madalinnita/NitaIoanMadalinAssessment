package com.example.nitaioanmadalinassessment.data.repository

import com.example.nitaioanmadalinassessment.data.api.ApiHelper

class ArticlesRepository(private val apiHelper: ApiHelper) {
    suspend fun getAllArticles() = apiHelper.getAllArticles()
}