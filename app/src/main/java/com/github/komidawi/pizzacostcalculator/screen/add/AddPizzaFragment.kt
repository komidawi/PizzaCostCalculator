package com.github.komidawi.pizzacostcalculator.screen.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabase
import com.github.komidawi.pizzacostcalculator.databinding.FragmentAddPizzaBinding
import com.github.komidawi.pizzacostcalculator.util.ViewModelFactory


class AddPizzaFragment : Fragment() {

    private lateinit var binding: FragmentAddPizzaBinding
    private lateinit var viewModel: AddPizzaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPizzaBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val databaseDao = PizzaDatabase.getInstance(application).pizzaDatabaseDao

        viewModel = ViewModelFactory(databaseDao).create(AddPizzaFragmentViewModel::class.java)
        binding.addPizzaFragmentViewModel = viewModel

        viewModel.navigateToPizzaListFragment.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                this.findNavController()
                    .navigate(AddPizzaFragmentDirections.actionAddPizzaFragmentToPizzaListFragment())
                viewModel.doneNavigating()
            }
        })

        // Specify the current activity as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = this

        return binding.root
    }
}