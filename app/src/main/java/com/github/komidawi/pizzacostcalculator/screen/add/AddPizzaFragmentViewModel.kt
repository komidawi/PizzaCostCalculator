package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.github.komidawi.pizzacostcalculator.calc.CostCalculator
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import com.github.komidawi.pizzacostcalculator.domain.PizzaFactory
import kotlinx.coroutines.*
import java.math.BigDecimal
import java.math.RoundingMode

class AddPizzaFragmentViewModel(
    private val pizzaRepository: PizzaRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val pizzeria = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    val size = MutableLiveData<String>()

    val price = MutableLiveData<String>()

    val deliveryCost = MutableLiveData<String>()

    private val _ratio = MutableLiveData(BigDecimal.ZERO)
    val ratio: LiveData<String>
        get() = _ratio.map { it.setScale(0, RoundingMode.HALF_UP).toString() }

    private val _navigateToPizzaListFragment = MutableLiveData(false)
    val navigateToPizzaListFragment: LiveData<Boolean>
        get() = _navigateToPizzaListFragment

    private val _displayEmptyFieldsToast = MutableLiveData(false)
    val displayEmptyFieldsToast: LiveData<Boolean>
        get() = _displayEmptyFieldsToast


    fun handleAddPizza(): Pizza? {
        val pizza = createPizza()
        if (pizza != null) {
            uiScope.launch {
                insertPizza(pizza)
                _navigateToPizzaListFragment.value = true
            }
        } else {
            _displayEmptyFieldsToast.value = true
        }
        return pizza
    }

    private suspend fun insertPizza(pizza: Pizza) {
        withContext(ioDispatcher) {
            pizzaRepository.insert(pizza)
        }
    }

    fun createPizza(): Pizza? {
        val currentPizzeria = pizzeria.value
        val currentName = name.value
        val currentSize = size.value
        val currentPrice = price.value
        val currentDeliveryCost = deliveryCost.value

        return if (currentPizzeria.isNullOrEmpty() || currentName.isNullOrEmpty()
            || currentSize.isNullOrEmpty() || currentPrice.isNullOrEmpty()
        ) {
            null
        } else {
            PizzaFactory.create(
                currentPizzeria,
                currentName,
                currentSize,
                currentPrice,
                currentDeliveryCost ?: "0"
            )
        }
    }

    fun calculateRatio(): BigDecimal {
        return if (!price.value.isNullOrEmpty() && !size.value.isNullOrEmpty() && BigDecimal(size.value) != BigDecimal.ZERO) {
            val totalPrice =
                if (!deliveryCost.value.isNullOrEmpty())
                    BigDecimal(price.value) + BigDecimal(deliveryCost.value)
                else BigDecimal(price.value)
            CostCalculator.calculateRatioPerSqMeter(BigDecimal(size.value), totalPrice)
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