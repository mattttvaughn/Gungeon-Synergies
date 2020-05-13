package io.github.mattpvaughn.synergies.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.mattpvaughn.synergies.data.local.model.Synergy

class DetailsViewModelFactory(private val synergy: Synergy) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            DetailsViewModel(synergy) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }


}
