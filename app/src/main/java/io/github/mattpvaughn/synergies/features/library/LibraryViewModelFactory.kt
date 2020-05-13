package io.github.mattpvaughn.synergies.features.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import javax.inject.Inject

class LibraryViewModelFactory(private val moshi: Moshi, private val json: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
            LibraryViewModel(json, moshi) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
