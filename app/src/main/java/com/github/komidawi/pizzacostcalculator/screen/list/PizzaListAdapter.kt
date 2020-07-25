package com.github.komidawi.pizzacostcalculator.screen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

class PizzaListAdapter :
    ListAdapter<PizzaEntity, PizzaListAdapter.PizzaItemViewHolder>(PizzaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaItemViewHolder {
        return PizzaItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PizzaItemViewHolder, position: Int) {
        val pizza = getItem(position)
        holder.bind(pizza)
    }

    class PizzaItemViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.pizza_name_display)
        private val size: TextView = itemView.findViewById(R.id.pizza_size_display)
        private val price: TextView = itemView.findViewById(R.id.pizza_price_display)

        fun bind(pizza: PizzaEntity) {
            name.text = pizza.name
            size.text = pizza.size.toString()
            price.text = pizza.price.toString()
        }

        companion object {
            fun from(parent: ViewGroup): PizzaItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val textView = inflater.inflate(R.layout.list_item_pizza, parent, false)
                return PizzaItemViewHolder(textView)
            }
        }
    }
}

class PizzaDiffCallback : DiffUtil.ItemCallback<PizzaEntity>() {
    override fun areItemsTheSame(oldItem: PizzaEntity, newItem: PizzaEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PizzaEntity, newItem: PizzaEntity) =
        oldItem == newItem
}