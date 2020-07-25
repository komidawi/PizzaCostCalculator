package com.github.komidawi.pizzacostcalculator.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabase
import com.github.komidawi.pizzacostcalculator.databinding.FragmentPizzaListBinding
import com.github.komidawi.pizzacostcalculator.util.ViewModelFactory

class PizzaListFragment : Fragment() {

    private lateinit var binding: FragmentPizzaListBinding
    private lateinit var viewModel: PizzaListFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPizzaListBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val databaseDao = PizzaDatabase.getInstance(application).pizzaDatabaseDao

        viewModel = ViewModelFactory(databaseDao).create(PizzaListFragmentViewModel::class.java)
        binding.pizzaListFragmentViewModel = viewModel

        val adapter = PizzaListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.pizzaList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        binding.addPizzaFab.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(PizzaListFragmentDirections.actionPizzaListFragmentToAddPizzaFragment())
        }

        return binding.root
    }
}