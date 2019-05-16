package com.example.gendx_android.functionalities.main.model

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.gendx_android.R
import com.example.gendx_android.data.model.Person
import java.util.zip.Inflater

class GenderAdapter(var items: ArrayList<Person>, val resources: Resources) : RecyclerView.Adapter<GenderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GenderViewHolder(inflater, parent, resources)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GenderViewHolder, position: Int) {
        val person = items[position]
        holder.bind(person, position + 1)
    }
}