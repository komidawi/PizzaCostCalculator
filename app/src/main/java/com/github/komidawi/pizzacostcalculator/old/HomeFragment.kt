package com.github.komidawi.pizzacostcalculator.old

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.github.komidawi.pizzacostcalculator.old.HomeFragmentArgs
import com.github.komidawi.pizzacostcalculator.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
//        showRandomNumberMessage()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun showRandomNumberMessage() {
        val args =
            HomeFragmentArgs.fromBundle(
                requireArguments()
            )
        Toast.makeText(context, "Random number: ${args.randomNumber}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item, requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
