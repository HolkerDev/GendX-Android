package com.example.gendx_android.functionalities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var event = MutableLiveData<MainEvents>()

    fun galleryButtonPressed() {
        event.value = MainEvents.ChooseFromGallery
    }

    fun photoButtonPressed() {
        event.value = MainEvents.TakePhoto
    }


}