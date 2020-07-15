package com.github.komidawi.pizzacostcalculator.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.github.komidawi.pizzacostcalculator.databinding.FragmentPizzaListBinding

class PizzaListFragment : Fragment() {

    private lateinit var binding: FragmentPizzaListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPizzaListBinding.inflate(inflater, container, false)

        binding.addPizzaFab.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(PizzaListFragmentDirections.actionPizzaListFragmentToAddPizzaFragment())
        }

        return binding.root
    }
}