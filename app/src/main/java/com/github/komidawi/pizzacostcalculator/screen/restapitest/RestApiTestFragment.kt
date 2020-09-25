package com.github.komidawi.pizzacostcalculator.screen.restapitest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.komidawi.pizzacostcalculator.databinding.RestApiTestFragmentBinding

class RestApiTestFragment : Fragment() {

    private lateinit var viewModel: RestApiTestFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RestApiTestFragmentBinding.inflate(inflater)

        viewModel = RestApiTestFragmentViewModel()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

}