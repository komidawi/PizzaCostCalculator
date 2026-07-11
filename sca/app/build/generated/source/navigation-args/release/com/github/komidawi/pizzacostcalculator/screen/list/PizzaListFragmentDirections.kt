package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.github.komidawi.pizzacostcalculator.R

public class PizzaListFragmentDirections private constructor() {
  public companion object {
    public fun actionPizzaListFragmentToAddPizzaFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_pizzaListFragment_to_addPizzaFragment)
  }
}
