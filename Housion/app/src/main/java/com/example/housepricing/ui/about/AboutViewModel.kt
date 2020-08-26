package com.example.housepricing.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Housion is a demonstration application\n" +
                "for prediction of house price.\n" +
                "Application only run for one given \n" +
                "neighborhood of given district\n" +
                "of given province.\n" +
                "Also the name 'Housion' is an abbreviation of\n" +
                "words 'Housing' and 'Prediction'\n" +
                "\nWe will publish new releases \n" +
                "by considering your feedback\n" +
                "that will give opportunities of usage with more province...\n"

    }
    val text: LiveData<String> = _text
}