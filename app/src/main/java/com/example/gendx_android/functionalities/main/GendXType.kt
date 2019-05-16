package com.example.gendx_android.functionalities.main

sealed class GendXType {
    object One : GendXType()
    object Many : GendXType()
}