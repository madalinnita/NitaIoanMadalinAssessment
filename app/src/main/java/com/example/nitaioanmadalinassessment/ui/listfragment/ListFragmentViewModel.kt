package com.example.nitaioanmadalinassessment.ui.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nitaioanmadalinassessment.data.repository.ArticlesRepository
import com.example.nitaioanmadalinassessment.data.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class ListFragmentViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    fun getAllArticles() = liveData(Dispatchers.IO) {
        // delay used to prove shimmer progress effect - need to be removed in a real app
        delay(2000)
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = articlesRepository.getAllArticles()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}