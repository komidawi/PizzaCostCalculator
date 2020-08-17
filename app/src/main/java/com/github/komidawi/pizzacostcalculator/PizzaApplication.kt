package com.github.komidawi.pizzacostcalculator

import android.app.Application
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao

class PizzaApplication : Application() {

    val pizzaDatabaseDao: PizzaDatabaseDao
        get() = ServiceLocator.provideDatabaseDao(this)
}