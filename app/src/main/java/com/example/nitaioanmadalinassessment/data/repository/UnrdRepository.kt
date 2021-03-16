package com.example.nitaioanmadalinassessment.data.repository

import com.example.nitaioanmadalinassessment.data.api.ApiHelper

class UnrdRepository(private val apiHelper: ApiHelper) {
    suspend fun getUnrdItems() = apiHelper.getUnrdItems()
}