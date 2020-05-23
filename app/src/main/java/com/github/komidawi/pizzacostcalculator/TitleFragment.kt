package com.github.komidawi.pizzacostcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.github.komidawi.pizzacostcalculator.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTitleBinding.inflate(inflater, container, false)

        binding.appStartButton.setOnClickListener { view: View ->
            val randomNumber = (0..10).random()
            view.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToHomeFragment(randomNumber))
        }

        return binding.root
    }
}
