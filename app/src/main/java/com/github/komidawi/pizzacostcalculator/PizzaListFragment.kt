package com.github.komidawi.pizzacostcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.komidawi.pizzacostcalculator.databinding.FragmentPizzaListBinding
import com.github.komidawi.pizzacostcalculator.model.PizzaModel
import java.math.BigDecimal

class PizzaListFragment : Fragment() {

    private val data = listOf(
        PizzaModel("Pizza1", BigDecimal(30), BigDecimal(20.11)),
        PizzaModel("Pizza2", BigDecimal(40), BigDecimal(30.12)),
        PizzaModel("Pizza3", BigDecimal(50), BigDecimal(40.13))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPizzaListBinding.inflate(inflater, container, false)
        binding.pizzaData.text = data.toString()
        return binding.root
    }
}