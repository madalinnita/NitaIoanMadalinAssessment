package com.example.nitaioanmadalinassessment.ui.listfragment.adapter

import com.example.nitaioanmadalinassessment.ui.data.models.Article

interface ItemClickedCallback {
    fun selectedArticle(article: Article)
}