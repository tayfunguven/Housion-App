package com.example.housepricing.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fill the necessary \ninformation about the house.\nSo, we will calculate a predicted price\nfor your dream house!\n\nLet's start calculation..."
    }
    val text: LiveData<String> = _text
}