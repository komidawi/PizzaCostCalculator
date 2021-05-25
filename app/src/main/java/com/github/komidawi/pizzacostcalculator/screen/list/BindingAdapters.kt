package com.github.komidawi.pizzacostcalculator.screen.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.komidawi.pizzacostcalculator.calc.CostCalculator
import com.github.komidawi.pizzacostcalculator.calc.CostCalculatorImpl
import com.github.komidawi.pizzacostcalculator.domain.Pizza

/*
    BindingAdapters are generally used to in terms of reusability or
    extracting complicated setter logic such as ImageView (down)loading
 */

private val costCalculator: CostCalculator = CostCalculatorImpl()

@BindingAdapter("pizzaPizzeriaDisplay")
fun TextView.setPizzaPizzeriaDisplay(pizza: Pizza?) {
    pizza?.let { text = pizza.pizzeria }
}

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

@BindingAdapter("pizzaDeliveryCostDisplay")
fun TextView.setPizzaDeliveryCostDisplay(pizza: Pizza?) {
    pizza?.let { text = String.format("%s", pizza.deliveryCost.toString()) }
}

@BindingAdapter("pizzaTotalCostDisplay")
fun TextView.setPizzaTotalCostDisplay(pizza: Pizza?) {
    pizza?.let { text = String.format("%s", pizza.getTotalCost()) }
}

@BindingAdapter("listPizzaRatioDisplay")
fun TextView.setListPizzaRatioDisplay(pizza: Pizza?) {
    pizza?.let {
        text = String.format(
            "%.0f (%.0f)",
            pizza.ratio.toDouble(),
            costCalculator.calculateRatioPerSqMeter(pizza.size, pizza.getTotalCost())
        )
    }
}