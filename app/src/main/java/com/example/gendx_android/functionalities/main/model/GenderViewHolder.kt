package com.example.gendx_android.functionalities.main.model

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gendx_android.R
import com.example.gendx_android.data.model.Person
import kotlinx.android.synthetic.main.item_gender.view.*
import java.text.FieldPosition

class GenderViewHolder(inflater: LayoutInflater, parent: ViewGroup, val resources: Resources) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_gender, parent, false)) {

    fun bind(person: Person, position: Int) {
        itemView.item_gender_tv_number.text = position.toString()
        itemView.item_gender_tv_gender.text = person.gender.toString()
        setIcon(person.gender)
    }

    private fun setIcon(gender: String) {
        when (gender) {
            "woman" -> {
                itemView.item_gender_icon.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_white_woman, null)
            }
            "man" -> {
                itemView.item_gender_icon.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_white_man, null)
            }
        }

    }

}