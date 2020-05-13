package io.github.mattpvaughn.synergies.navigation

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import io.github.mattpvaughn.synergies.features.details.DetailsFragment
import io.github.mattpvaughn.synergies.features.library.LibraryFragment
import io.github.mattpvaughn.synergies.R
import io.github.mattpvaughn.synergies.data.local.model.Synergy
import io.github.mattpvaughn.synergies.features.details.DetailsFragment.Companion.ARG_SYNERGY

class Navigator(private val fragmentManager: FragmentManager) {

    private val libraryFragment by lazy {
        LibraryFragment.newInstance()
    }

    fun openLibrary() {
        fragmentManager.beginTransaction().replace(R.id.fragNavHost, libraryFragment).commit()
    }

    fun openDetails(synergy: Synergy) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragNavHost, DetailsFragment.newInstance().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SYNERGY, synergy)
                }
            }).addToBackStack(null).commit()
    }

    fun onBackPressed() {
        fragmentManager.popBackStack()
    }

}