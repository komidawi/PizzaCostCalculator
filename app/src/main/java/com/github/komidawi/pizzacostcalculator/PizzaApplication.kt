package com.github.komidawi.pizzacostcalculator

import android.app.Application
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository

class PizzaApplication : Application() {

    val pizzaRepository: PizzaRepository
        get() = ServiceLocator.providePizzaRepository(this)
}