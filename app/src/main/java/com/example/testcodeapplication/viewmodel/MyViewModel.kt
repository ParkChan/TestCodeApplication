package com.example.testcodeapplication.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _liveData1 = MutableLiveData<String>()
    val liveData1: LiveData<String> = _liveData1

    // [Transformations.map] on liveData1 that converts the value to uppercase:
    val liveData2 = _liveData1.map { it.toUpperCase() }

    fun setNewValue(newValue: String) = viewModelScope.launch{
        _liveData1.value = newValue
    }
}