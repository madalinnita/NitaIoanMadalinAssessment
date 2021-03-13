package com.example.nitaioanmadalinassessment.ui.listfragment.adapter

import com.example.nitaioanmadalinassessment.data.models.articles.Article

interface ItemClickedCallback {
    fun selectedArticle(article: Article)
}