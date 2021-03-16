package com.example.nitaioanmadalinassessment.ui.unrditemsfragment

import UnrdResponse
import androidx.lifecycle.*
import com.example.nitaioanmadalinassessment.data.models.articles.ArticlesResponse
import com.example.nitaioanmadalinassessment.data.repository.ArticlesRepository
import com.example.nitaioanmadalinassessment.data.repository.UnrdRepository
import com.example.nitaioanmadalinassessment.data.utils.Resource
import kotlinx.coroutines.*

class UnrdFragmentViewModel(
    private val unrdRepository: UnrdRepository, private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val unrdItemsResponse = MutableLiveData<Resource<UnrdResponse>>()

    fun getUnrdItemsNow() {
        viewModelScope.launch(defaultDispatcher) {
            unrdItemsResponse.postValue(Resource.loading(data = null))
            withContext(Dispatchers.IO) {
                try {
                    unrdItemsResponse.postValue(Resource.success(data = unrdRepository.getUnrdItems()))
                } catch (exception: java.lang.Exception) {
                    unrdItemsResponse.postValue(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
                }
            }
        }
    }


}