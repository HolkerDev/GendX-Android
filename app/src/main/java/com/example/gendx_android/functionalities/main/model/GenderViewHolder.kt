package com.example.gendx_android.functionalities.main.model

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gendx_android.R
import com.example.gendx_android.data.model.Person

class GenderViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_gender, parent, false)) {

    fun bind(person: Person) {

    }
}