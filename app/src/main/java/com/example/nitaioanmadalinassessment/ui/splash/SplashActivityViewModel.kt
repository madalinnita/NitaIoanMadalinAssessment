package com.example.nitaioanmadalinassessment.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nitaioanmadalinassessment.ui.splash.utils.SplashState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivityViewModel(application: Application): AndroidViewModel(application) {

    private val delayTime: Long = 3000
    val splashState = MutableLiveData<SplashState>()

    fun initSplashScreen() {
        viewModelScope.launch {
            splashState.value = SplashState.STARTED
            delay(delayTime)
            splashState.value = SplashState.FINISHED
        }
    }

}