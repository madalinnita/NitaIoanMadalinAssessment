package com.example.nitaioanmadalinassessment.ui.data.models.articles

import com.google.gson.annotations.SerializedName

data class ArticleSource(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)