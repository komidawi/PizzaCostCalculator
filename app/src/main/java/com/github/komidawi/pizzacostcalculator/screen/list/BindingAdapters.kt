package com.github.komidawi.pizzacostcalculator.screen.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

/*
    BindingAdapters are generally used to in terms of reusability or
    extracting complicated setter logic such as ImageView (down)loading
 */

@BindingAdapter("pizzaNameDisplay")
fun TextView.setPizzaNameDisplay(pizza: PizzaEntity?) {
    pizza?.let { text = pizza.name }
}

@BindingAdapter("pizzaSizeDisplay")
fun TextView.setPizzaSizeDisplay(pizza: PizzaEntity?) {
    pizza?.let { text = pizza.size.toString() }
}

@BindingAdapter("pizzaPriceDisplay")
fun TextView.setPizzaPriceDisplay(pizza: PizzaEntity?) {
    pizza?.let { text = pizza.price.toString() }
}

@BindingAdapter("listPizzaRatioDisplay")
fun TextView.setListPizzaRatioDisplay(pizza: PizzaEntity?){
    pizza?.let { text = pizza.ratio.toString() }
}