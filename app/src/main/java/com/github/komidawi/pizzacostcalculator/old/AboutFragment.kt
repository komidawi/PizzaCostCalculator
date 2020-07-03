package com.github.komidawi.pizzacostcalculator.old

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.app.ShareCompat
import com.github.komidawi.pizzacostcalculator.R

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.about_menu, menu)
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            menu.findItem(R.id.share).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    private fun getShareIntent(): Intent {
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText("I'm usin' wonderful Pizza App!")
            .setType("text/plain")
            .intent
    }
}
