package com.example.nitaioanmadalinassessment.ui.listfragment.adapter

import com.example.nitaioanmadalinassessment.ui.data.models.articles.ArticlesResponse

interface ItemClickedCallback {
    fun selectedArticle(article: ArticlesResponse)
}