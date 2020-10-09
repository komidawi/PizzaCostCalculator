package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import com.github.komidawi.pizzacostcalculator.network.RestApi
import com.github.komidawi.pizzacostcalculator.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PizzaListFragmentViewModel(private val pizzaRepository: PizzaRepository) : ViewModel() {

    private val _displayFetchStatusToast = MutableLiveData(false)
    val displayFetchStatusToast: LiveData<Boolean>
        get() = _displayFetchStatusToast

    private val _fetchStatusMessage = MutableLiveData<String>()
    val fetchStatusMessage: LiveData<String>
        get() = _fetchStatusMessage

    val pizzaList = pizzaRepository.getAll()

    init {
        fetchAllPizzas()
    }

    private fun fetchAllPizzas() {
        viewModelScope.launch {
            try {
                val allPizzas = RestApi.retrofitService.getAllPizzas()
                // TODO: temporary solution until Remote DataSource provided
                pizzaRepository.deleteAll()
                pizzaRepository.insertAll(allPizzas.asDomainModel())
                _fetchStatusMessage.value = "Success, ${allPizzas.size} fetched"
            } catch (e: Exception) {
                _fetchStatusMessage.value = "Error while fetching pizzas: ${e.message}"
            }
            _displayFetchStatusToast.value = true
        }
    }

    fun onRemove(pizza: Pizza) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pizzaRepository.deleteByUuid(pizza.uuid)
            }
        }
    }

    fun onClear() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pizzaRepository.deleteAll()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}