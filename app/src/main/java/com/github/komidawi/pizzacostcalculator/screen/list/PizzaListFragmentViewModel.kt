package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import kotlinx.coroutines.*

class PizzaListFragmentViewModel(
    private val pizzaRepository: PizzaRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var sortingMode = MutableLiveData(SortingMode.RATIO_ASCENDING)

    val pizzaList = pizzaRepository.getAll()

    fun onRemove(pizza: Pizza) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                pizzaRepository.deleteByUuid(pizza.uuid)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}