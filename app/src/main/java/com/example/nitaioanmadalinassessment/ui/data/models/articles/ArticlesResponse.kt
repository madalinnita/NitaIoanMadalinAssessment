package com.example.nitaioanmadalinassessment.ui.data.models.articles

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(@SerializedName("status") val status: String,
                            @SerializedName("totalResults") val totalResults: Int,
                            val image: Drawable)