package com.example.restfulapp.ui.duckinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DuckinfoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is duckinfo Fragment"
    }
    val text: LiveData<String> = _text
}