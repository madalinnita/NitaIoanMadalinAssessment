package com.example.nitaioanmadalinassessment.ui.data.repository

import com.example.nitaioanmadalinassessment.ui.data.api.ApiHelper

class ArticlesRepository(private val apiHelper: ApiHelper) {
    suspend fun getAllArticles() = apiHelper.getAllArticles()
}