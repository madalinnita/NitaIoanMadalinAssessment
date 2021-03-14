package com.example.nitaioanmadalinassessment.data.models.articles

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleSource(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
): Serializable