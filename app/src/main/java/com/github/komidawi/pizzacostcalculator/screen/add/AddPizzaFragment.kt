package com.github.komidawi.pizzacostcalculator.screen.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.komidawi.pizzacostcalculator.PizzaApplication
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.databinding.FragmentAddPizzaBinding
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory


class AddPizzaFragment : Fragment() {

    private lateinit var binding: FragmentAddPizzaBinding
    private lateinit var viewModel: AddPizzaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPizzaBinding.inflate(inflater, container, false)

        val pizzaRepository =
            (requireContext().applicationContext as PizzaApplication).pizzaRepository

        viewModel = ViewModelFactory(pizzaRepository).create(AddPizzaFragmentViewModel::class.java)
        binding.viewModel = viewModel

        setupNavigateToPizzaListObserver()
        setupDisplayEmptyFieldsToastObserver()
        setupUpdateRatioListeners()

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun setupNavigateToPizzaListObserver() {
        viewModel.navigateToPizzaListFragment.observe(viewLifecycleOwner, { navigate ->
            if (navigate) {
                this.findNavController()
                    .navigate(AddPizzaFragmentDirections.actionAddPizzaFragmentToPizzaListFragment())
                viewModel.doneNavigating()
            }
        })
    }

    private fun setupDisplayEmptyFieldsToastObserver() {
        viewModel.displayEmptyFieldsToast.observe(viewLifecycleOwner, { displayToast ->
            if (displayToast) {
                val message = getString(R.string.all_fields_are_required_message)
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                viewModel.doneDisplayingEmptyFieldsToast()
            }
        })
    }

    private fun setupUpdateRatioListeners() {
        binding.pizzaSizeInput.addTextChangedListener {
            viewModel.updateRatioDisplay()
        }
        binding.pizzaPriceInput.addTextChangedListener {
            viewModel.updateRatioDisplay()
        }
        binding.pizzaDeliveryCostInput.addTextChangedListener {
            viewModel.updateRatioDisplay()
        }
    }
}