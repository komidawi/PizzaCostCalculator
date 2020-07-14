package com.github.komidawi.pizzacostcalculator.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.komidawi.pizzacostcalculator.databinding.FragmentAddPizzaBinding
import com.github.komidawi.pizzacostcalculator.util.ViewModelFactory


class AddPizzaFragment : Fragment() {

    private lateinit var binding: FragmentAddPizzaBinding
    private lateinit var addPizzaFragmentViewModel: AddPizzaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPizzaBinding.inflate(layoutInflater, container, false)

        addPizzaFragmentViewModel = ViewModelFactory.create(AddPizzaFragmentViewModel::class.java)
        binding.addPizzaFragmentViewModel = addPizzaFragmentViewModel

        // Specify the current activity as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        // binding.lifecycleOwner = this // Czy to potrzebne?

        return binding.root
    }
}