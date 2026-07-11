package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.github.komidawi.pizzacostcalculator.R

public class AddPizzaFragmentDirections private constructor() {
  public companion object {
    public fun actionAddPizzaFragmentToPizzaListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_addPizzaFragment_to_pizzaListFragment)
  }
}
