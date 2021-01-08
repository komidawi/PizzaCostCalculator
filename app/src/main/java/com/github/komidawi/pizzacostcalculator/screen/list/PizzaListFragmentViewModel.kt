package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.lifecycle.LiveData
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

    private val _displayFetchStatusToast = MutableLiveData(false)
    val displayFetchStatusToast: LiveData<Boolean>
        get() = _displayFetchStatusToast

    private val _fetchStatusMessage = MutableLiveData<String>()
    val fetchStatusMessage: LiveData<String>
        get() = _fetchStatusMessage

    val pizzaList = pizzaRepository.getAll()


    fun onRemove(pizza: Pizza) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                pizzaRepository.deleteByUuid(pizza.uuid)
            }
        }
    }

    fun onClear() {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                pizzaRepository.deleteAll()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}