package com.example.housepricing.ui.prediction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Step3ViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Building Information"
    }
    val text: LiveData<String> = _text
}