package com.example.nitaioanmadalinassessment.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var newCategory = MutableLiveData<String>()
    var recyclerViewIsScrolled = MutableLiveData<Boolean>()
    var showBackArrowAsToolbar = MutableLiveData<Boolean>()
}