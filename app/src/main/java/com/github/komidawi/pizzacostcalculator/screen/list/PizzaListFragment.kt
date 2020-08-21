package com.github.komidawi.pizzacostcalculator.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.github.komidawi.pizzacostcalculator.PizzaApplication
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabase
import com.github.komidawi.pizzacostcalculator.databinding.FragmentPizzaListBinding
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory

class PizzaListFragment : Fragment() {

    private lateinit var binding: FragmentPizzaListBinding
    private lateinit var viewModel: PizzaListFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPizzaListBinding.inflate(inflater, container, false)

        val databaseDao = (requireContext().applicationContext as PizzaApplication).pizzaDatabaseDao

        viewModel = ViewModelFactory(databaseDao).create(PizzaListFragmentViewModel::class.java)
        binding.pizzaListFragmentViewModel = viewModel

        setupRecyclerView()
        setupFabOnClickListener()

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun setupRecyclerView() {
        val clickListener = PizzaItemListener { pizza ->
            Toast.makeText(context, "$pizza", Toast.LENGTH_SHORT).show()
        }
        val adapter = PizzaListAdapter(clickListener, viewModel)

        binding.pizzaListRecyclerView.adapter = adapter

        viewModel.pizzaList.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.addHeaderAndSubmitList(it) }
        })
    }

    private fun setupFabOnClickListener() {
        binding.addPizzaFab.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(PizzaListFragmentDirections.actionPizzaListFragmentToAddPizzaFragment())
        }
    }
}