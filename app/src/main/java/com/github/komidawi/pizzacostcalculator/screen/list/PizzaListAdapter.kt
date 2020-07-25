package com.github.komidawi.pizzacostcalculator.screen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

class PizzaListAdapter : RecyclerView.Adapter<PizzaListAdapter.PizzaItemViewHolder>() {
    var data = listOf<PizzaEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val textView = inflater.inflate(R.layout.list_item_pizza, parent, false)
        return PizzaItemViewHolder(textView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PizzaItemViewHolder, position: Int) {
        val pizza = data[position]
        holder.apply {
            name.text = pizza.name
            size.text = pizza.size.toString()
            price.text = pizza.price.toString()
        }
    }

    class PizzaItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.pizza_name_display)
        val size: TextView = itemView.findViewById(R.id.pizza_size_display)
        val price: TextView = itemView.findViewById(R.id.pizza_price_display)
    }
}
