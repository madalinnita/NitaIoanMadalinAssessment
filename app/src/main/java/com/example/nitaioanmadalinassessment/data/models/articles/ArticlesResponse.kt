package com.example.nitaioanmadalinassessment.data.models.articles

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(@SerializedName("status") val status: String,
                            @SerializedName("totalResults") val totalResults: Int,
                            @SerializedName("articles") val articles: List<Article>)