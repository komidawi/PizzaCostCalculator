package com.github.komidawi.pizzacostcalculator.screen.restapitest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.komidawi.pizzacostcalculator.network.RestApi
import kotlinx.coroutines.launch

class RestApiTestFragmentViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        fetchAllPizzas()
    }

    private fun fetchAllPizzas() {
        viewModelScope.launch {
            try {
                val allPizzas = RestApi.retrofitService.getAllPizzas()
                _response.value = "Success, ${allPizzas.size} fetched"
            } catch (e: Exception) {
                _response.value = "Error while fetching pizzas: ${e.message}"
            }
        }
    }
}