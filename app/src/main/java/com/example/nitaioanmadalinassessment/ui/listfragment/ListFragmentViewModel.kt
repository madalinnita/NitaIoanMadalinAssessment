package com.example.nitaioanmadalinassessment.ui.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nitaioanmadalinassessment.ui.data.repository.ArticlesRepository
import com.example.nitaioanmadalinassessment.ui.data.utils.Resource
import kotlinx.coroutines.Dispatchers

class ListFragmentViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    fun getAllArticles() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = articlesRepository.getAllArticles()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}