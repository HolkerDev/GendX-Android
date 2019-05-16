package com.example.gendx_android.functionalities.main

sealed class MainEvents {
    object TakePhoto : MainEvents()
    object ChooseFromGallery : MainEvents()
}