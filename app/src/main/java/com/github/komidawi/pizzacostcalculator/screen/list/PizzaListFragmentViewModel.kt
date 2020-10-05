package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.komidawi.pizzacostcalculator.data.PizzaRepository
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.network.RestApi
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
                pizzaRepository.insertAll(allPizzas)
                _fetchStatusMessage.value = "Success, ${allPizzas.size} fetched"
            } catch (e: Exception) {
                _fetchStatusMessage.value = "Error while fetching pizzas: ${e.message}"
            }
            _displayFetchStatusToast.value = true
        }
    }

    fun onRemove(pizza: PizzaEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pizzaRepository.deleteById(pizza.id)
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