package com.github.komidawi.pizzacostcalculator.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.github.komidawi.pizzacostcalculator.database.SampleRepository
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
        viewModel =
            ViewModelFactory(SampleRepository).create(PizzaListFragmentViewModel::class.java)
        binding.pizzaListFragmentViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        binding.addPizzaFab.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(PizzaListFragmentDirections.actionPizzaListFragmentToAddPizzaFragment())
        }

        val args = PizzaListFragmentArgs.fromBundle(requireArguments())
        val pizza = args.addedPizza
        if (pizza != null) {
            viewModel.addPizza(pizza)
            Toast.makeText(context, pizza.toString(), Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}