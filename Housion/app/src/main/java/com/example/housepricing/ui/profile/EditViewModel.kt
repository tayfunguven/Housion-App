package com.example.housepricing.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Edit part"
    }
    val text: LiveData<String> = _text

}