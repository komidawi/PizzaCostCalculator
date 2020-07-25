package com.github.komidawi.pizzacostcalculator.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

class PizzaListAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<PizzaEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val textView = inflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(textView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        holder.textView.text = data[position].name
    }
}

class TextItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
