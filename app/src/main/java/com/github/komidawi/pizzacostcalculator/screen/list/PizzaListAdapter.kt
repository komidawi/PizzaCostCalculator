package com.github.komidawi.pizzacostcalculator.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.komidawi.pizzacostcalculator.databinding.ListItemPizzaElementBinding
import com.github.komidawi.pizzacostcalculator.domain.Pizza

class PizzaListAdapter(
    private val clickListener: PizzaItemListener,
    private val pizzaListFragmentViewModel: PizzaListFragmentViewModel
) :
    ListAdapter<Pizza, PizzaListAdapter.PizzaItemViewHolder>(PizzaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaItemViewHolder =
        PizzaItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: PizzaItemViewHolder, position: Int) {
        val pizzaItem = getItem(position)
        holder.bind(clickListener, pizzaItem, pizzaListFragmentViewModel)
    }

    // TODO: add coroutine
    fun sortAndSubmitList(list: List<Pizza>?, sortingMode: SortingMode?) {
        val items = list?.let {
            when (sortingMode) {
                SortingMode.RATIO_ASCENDING -> it.sortedBy(Pizza::ratio)
                SortingMode.RATIO_DESCENDING -> it.sortedByDescending(Pizza::ratio)
                SortingMode.PIZZERIA_ASCENDING -> it.sortedBy(Pizza::pizzeria)
                SortingMode.PIZZERIA_DESCENDING -> it.sortedByDescending(Pizza::pizzeria)
                else -> it
            }
        }
        submitList(items)
    }

    class PizzaItemViewHolder private constructor(private val binding: ListItemPizzaElementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            clickListener: PizzaItemListener,
            pizza: Pizza,
            pizzaListFragmentViewModel: PizzaListFragmentViewModel
        ) {
            binding.pizza = pizza
            binding.clickListener = clickListener
            binding.viewModel = pizzaListFragmentViewModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PizzaItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemPizzaElementBinding.inflate(inflater, parent, false)
                return PizzaItemViewHolder(binding)
            }
        }
    }
}

class PizzaDiffCallback : DiffUtil.ItemCallback<Pizza>() {

    override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza) = oldItem.uuid == newItem.uuid

    override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza) = oldItem == newItem
}

class PizzaItemListener(val clickListener: (pizza: Pizza) -> Unit) {
    fun onClick(pizza: Pizza) = clickListener(pizza)
}