package com.github.komidawi.pizzacostcalculator.screen.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.komidawi.pizzacostcalculator.domain.Pizza

/*
    BindingAdapters are generally used to in terms of reusability or
    extracting complicated setter logic such as ImageView (down)loading
 */

@BindingAdapter("pizzaNameDisplay")
fun TextView.setPizzaNameDisplay(pizza: Pizza?) {
    pizza?.let { text = pizza.name }
}

@BindingAdapter("pizzaSizeDisplay")
fun TextView.setPizzaSizeDisplay(pizza: Pizza?) {
    pizza?.let { text = String.format("%s", pizza.size.toString()) }
}

@BindingAdapter("pizzaPriceDisplay")
fun TextView.setPizzaPriceDisplay(pizza: Pizza?) {
    pizza?.let { text = String.format("%s", pizza.price.toString()) }
}

@BindingAdapter("listPizzaRatioDisplay")
fun TextView.setListPizzaRatioDisplay(pizza: Pizza?) {
    pizza?.let { text = String.format("%.0f", pizza.ratio.toDouble()) }
}