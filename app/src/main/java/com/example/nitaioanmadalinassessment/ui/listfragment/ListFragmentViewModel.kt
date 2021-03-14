package com.example.nitaioanmadalinassessment.ui.listfragment

import androidx.lifecycle.*
import com.example.nitaioanmadalinassessment.data.models.articles.ArticlesResponse
import com.example.nitaioanmadalinassessment.data.repository.ArticlesRepository
import com.example.nitaioanmadalinassessment.data.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFragmentViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    val articlesResponse = MutableLiveData<Resource<ArticlesResponse>>()

    fun getAllArticlesNow(category: String) {
        viewModelScope.launch {
            articlesResponse.postValue(Resource.loading(data = null))
            delay(2000)
            withContext(Dispatchers.IO) {
                try {
                    articlesResponse.postValue(Resource.success(data = articlesRepository.getAllArticles(category)))
                } catch (exception: java.lang.Exception) {
                    articlesResponse.postValue(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
                }
            }
        }
    }


}