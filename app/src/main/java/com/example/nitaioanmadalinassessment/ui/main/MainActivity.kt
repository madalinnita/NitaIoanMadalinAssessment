package com.example.nitaioanmadalinassessment.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.nitaioanmadalinassessment.R

class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}