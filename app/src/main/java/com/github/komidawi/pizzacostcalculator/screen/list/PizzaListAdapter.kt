package com.github.komidawi.pizzacostcalculator.screen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.databinding.ListItemPizzaBinding
import java.lang.ClassCastException

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1


class PizzaListAdapter(private val clickListener: PizzaItemListener) :
    ListAdapter<PizzaListItemType, RecyclerView.ViewHolder>(PizzaDiffCallback()) {

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
                holder.bind(clickListener, pizzaItem.pizza)
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
    fun addHeaderAndSubmitList(list: List<PizzaEntity>?) {
        val items = when (list) {
            null -> listOf(PizzaListItemType.Header)
            else -> listOf(PizzaListItemType.Header) + list.map { PizzaListItemType.PizzaListItem(it) }
        }
        submitList(items)
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
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PizzaListItemType, newItem: PizzaListItemType) =
        oldItem == newItem
}

class PizzaItemListener(val clickListener: (pizzaId: Long) -> Unit) {
    fun onClick(pizza: PizzaEntity) = clickListener(pizza.id)
}

sealed class PizzaListItemType {

    /**
     * Property needed especially for DiffUtil to determine changes basing on ids
     */
    abstract val id: Long

    data class PizzaListItem(val pizza: PizzaEntity) : PizzaListItemType() {
        override val id: Long = pizza.id
    }

    object Header : PizzaListItemType() {
        override val id: Long = Long.MIN_VALUE
    }
}