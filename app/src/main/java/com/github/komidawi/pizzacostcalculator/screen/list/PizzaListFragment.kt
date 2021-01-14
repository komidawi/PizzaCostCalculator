package com.github.komidawi.pizzacostcalculator.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.github.komidawi.pizzacostcalculator.PizzaApplication
import com.github.komidawi.pizzacostcalculator.databinding.FragmentPizzaListBinding
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory

class PizzaListFragment : Fragment() {

    private lateinit var binding: FragmentPizzaListBinding
    private lateinit var viewModel: PizzaListFragmentViewModel
    private lateinit var adapter: PizzaListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPizzaListBinding.inflate(inflater, container, false)

        val pizzaRepository =
            (requireContext().applicationContext as PizzaApplication).pizzaRepository

        viewModel = ViewModelFactory(pizzaRepository).create(PizzaListFragmentViewModel::class.java)
        binding.viewModel = viewModel

        setupRecyclerView()
        setupArrayAdapter()
        setupFabOnClickListener()

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun setupArrayAdapter() {
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            SortingMode.values()
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sortingModeSpinner.apply {
                adapter = arrayAdapter
                onItemSelectedListener = SortingModeSpinnerListener(viewModel)
            }
        }
    }

    private fun updateList() {
        val pizzas = viewModel.pizzaList.value
        val sortingMode = viewModel.sortingMode.value

        adapter.sortAndSubmitList(pizzas, sortingMode)
    }

    private fun setupRecyclerView() {
        val clickListener = PizzaItemListener { pizza ->
            Toast.makeText(context, "$pizza", Toast.LENGTH_SHORT).show()
        }

        adapter = PizzaListAdapter(clickListener, viewModel)
        binding.pizzaListRecyclerView.adapter = adapter

        viewModel.apply {
            pizzaList.observe(viewLifecycleOwner, { updateList() })
            sortingMode.observe(viewLifecycleOwner, { updateList() })
        }
    }

    private fun setupFabOnClickListener() {
        binding.addPizzaFab.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(PizzaListFragmentDirections.actionPizzaListFragmentToAddPizzaFragment())
        }
    }

    class SortingModeSpinnerListener(private val viewModel: PizzaListFragmentViewModel) :
        AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.sortingMode.value = SortingMode.values()[position]
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}