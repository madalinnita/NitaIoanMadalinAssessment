package com.example.nitaioanmadalinassessment.ui.unrditemsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nitaioanmadalinassessment.data.api.ApiHelper
import com.example.nitaioanmadalinassessment.data.repository.ArticlesRepository
import com.example.nitaioanmadalinassessment.data.repository.UnrdRepository
import kotlinx.coroutines.Dispatchers

class UnrdFragmentViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnrdFragmentViewModel::class.java)) {
            return UnrdFragmentViewModel(UnrdRepository(apiHelper), Dispatchers.IO) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}