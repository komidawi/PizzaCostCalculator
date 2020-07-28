package com.github.komidawi.pizzacostcalculator.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.databinding.ListItemPizzaBinding

class PizzaListAdapter(private val clickListener: PizzaItemListener) :
    ListAdapter<PizzaEntity, PizzaListAdapter.PizzaItemViewHolder>(PizzaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaItemViewHolder {
        return PizzaItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PizzaItemViewHolder, position: Int) {
        val pizza = getItem(position)!!
        holder.bind(clickListener, pizza)
    }


    class PizzaItemViewHolder private constructor(private val binding: ListItemPizzaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: PizzaItemListener, pizza: PizzaEntity) {
            binding.pizzaEntity = pizza
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PizzaItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemPizzaBinding.inflate(inflater, parent, false)
                return PizzaItemViewHolder(binding)
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

class PizzaItemListener(val clickListener: (pizzaId: Long) -> Unit) {
    fun onClick(pizza: PizzaEntity) = clickListener(pizza.id)
}