package com.github.komidawi.pizzacostcalculator.screen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.databinding.ListItemPizzaElementBinding
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1


class PizzaListAdapter(
    private val clickListener: PizzaItemListener,
    private val pizzaListFragmentViewModel: PizzaListFragmentViewModel
) :
    ListAdapter<PizzaListItemType, RecyclerView.ViewHolder>(PizzaDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> PizzaItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown type $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PizzaItemViewHolder -> {
                val pizzaItem = getItem(position) as PizzaListItemType.PizzaListItem
                holder.bind(clickListener, pizzaItem.pizza, pizzaListFragmentViewModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is PizzaListItemType.Header -> ITEM_VIEW_TYPE_HEADER
            is PizzaListItemType.PizzaListItem -> ITEM_VIEW_TYPE_ITEM
        }

    /**
     * Modifies the list by adding the header at the beginning.
     */
    fun addHeaderAndSubmitList(list: List<Pizza>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(PizzaListItemType.Header)
                else -> listOf(PizzaListItemType.Header) +
                        list.map { PizzaListItemType.PizzaListItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
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

    class HeaderViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_pizza_header, parent, false)
                return HeaderViewHolder(view)
            }
        }
    }
}

class PizzaDiffCallback : DiffUtil.ItemCallback<PizzaListItemType>() {
    override fun areItemsTheSame(oldItem: PizzaListItemType, newItem: PizzaListItemType) =
        oldItem.uuid == newItem.uuid

    override fun areContentsTheSame(oldItem: PizzaListItemType, newItem: PizzaListItemType) =
        oldItem == newItem
}

class PizzaItemListener(val clickListener: (pizza: Pizza) -> Unit) {
    fun onClick(pizza: Pizza) = clickListener(pizza)
}

sealed class PizzaListItemType {

    /**
     * Property needed especially for DiffUtil to determine changes basing on ids
     */
    abstract val uuid: String

    data class PizzaListItem(val pizza: Pizza) : PizzaListItemType() {
        override val uuid: String = pizza.uuid.toString()
    }

    object Header : PizzaListItemType() {
        override val uuid: String = "6324b8ed-b268-4225-8764-393a64079425"
    }
}