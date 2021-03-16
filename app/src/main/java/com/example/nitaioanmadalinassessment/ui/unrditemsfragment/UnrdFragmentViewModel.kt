package com.example.nitaioanmadalinassessment.ui.unrditemsfragment

import UnrdResponse
import androidx.lifecycle.*
import com.example.nitaioanmadalinassessment.data.models.articles.ArticlesResponse
import com.example.nitaioanmadalinassessment.data.repository.ArticlesRepository
import com.example.nitaioanmadalinassessment.data.repository.UnrdRepository
import com.example.nitaioanmadalinassessment.data.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UnrdFragmentViewModel(
    private val unrdRepository: UnrdRepository
) : ViewModel() {

    val unrdItemsResponse = MutableLiveData<Resource<UnrdResponse>>()

    fun getUnrdItemsNow() {
        viewModelScope.launch {
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