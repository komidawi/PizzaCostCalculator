package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.github.komidawi.pizzacostcalculator.calc.CostCalculator
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntityFactory
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import kotlinx.coroutines.*
import java.math.BigDecimal
import java.math.RoundingMode

class AddPizzaFragmentViewModel(private val pizzaRepository: PizzaRepository) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val name = MutableLiveData<String>()

    val size = MutableLiveData<String>()

    val price = MutableLiveData<String>()

    private val _ratio = MutableLiveData<BigDecimal>(BigDecimal.ZERO)
    val ratio: LiveData<String>
        get() = _ratio.map { it.setScale(0, RoundingMode.HALF_UP).toString() }

    private val _navigateToPizzaListFragment = MutableLiveData<Boolean>(false)
    val navigateToPizzaListFragment: LiveData<Boolean>
        get() = _navigateToPizzaListFragment

    private val _displayEmptyFieldsToast = MutableLiveData<Boolean>(false)
    val displayEmptyFieldsToast: LiveData<Boolean>
        get() = _displayEmptyFieldsToast


    fun handleAddPizza() {
        val pizza = createPizza()
        if (pizza != null) {
            uiScope.launch {
                insertPizza(pizza)
                _navigateToPizzaListFragment.value = true
            }
        } else {
            _displayEmptyFieldsToast.value = true
        }
    }

    private suspend fun insertPizza(pizza: PizzaEntity) {
        withContext(Dispatchers.IO) {
            pizzaRepository.insert(pizza)
        }
    }

    fun createPizza(): PizzaEntity? {
        val currentName = name.value
        val currentSize = size.value
        val currentPrice = price.value

        return if (currentName.isNullOrEmpty() || currentSize.isNullOrEmpty() || currentPrice.isNullOrEmpty()) {
            null
        } else {
            PizzaEntityFactory.create(currentName, currentSize, currentPrice)
        }
    }

    fun calculateRatio(): BigDecimal {
        return if (!price.value.isNullOrEmpty() && !size.value.isNullOrEmpty() && BigDecimal(size.value) != BigDecimal.ZERO) {
            CostCalculator.calculateRatioPerSqMeter(
                BigDecimal(size.value),
                BigDecimal(price.value)
            )
        } else {
            BigDecimal.ZERO
        }
    }

    fun updateRatioDisplay() {
        _ratio.value = calculateRatio()
    }

    fun doneNavigating() {
        _navigateToPizzaListFragment.value = false
    }

    fun doneDisplayingEmptyFieldsToast() {
        _displayEmptyFieldsToast.value = false
    }
}