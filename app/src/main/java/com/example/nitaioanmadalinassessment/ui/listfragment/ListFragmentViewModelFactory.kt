package com.example.nitaioanmadalinassessment.ui.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nitaioanmadalinassessment.data.api.ApiHelper
import com.example.nitaioanmadalinassessment.data.repository.ArticlesRepository

class ListFragmentViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            return ListFragmentViewModel(ArticlesRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}