package io.github.mattpvaughn.synergies.features.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.github.mattpvaughn.synergies.data.local.model.Synergy
import io.github.mattpvaughn.synergies.util.containsCaseInsensitive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LibraryViewModel(private val json: String, private val moshi: Moshi) : ViewModel() {
    private var _synergies = MutableLiveData<List<Synergy>>()
    val synergies: LiveData<List<Synergy>>
        get() = _synergies

    private lateinit var synergyList : List<Synergy>

    private var _search = MutableLiveData<String>()
    val search : LiveData<String>
        get() = _search

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val listType = Types.newParameterizedType(List::class.java, Synergy::class.java)
            val adapter = moshi.adapter<List<Synergy>>(listType)
            synergyList = adapter.fromJson(json) ?: emptyList()
            _synergies.postValue(synergyList)
        }
    }

    fun query(s: String) {
        if (s.isEmpty()) {
            _synergies.postValue(synergyList)
        }
        viewModelScope.launch {
            val matching = mutableSetOf<Synergy>()
            matching.addAll(synergyList.filter { it.description.containsCaseInsensitive(s) })
            matching.addAll(synergyList.filter { it.name.containsCaseInsensitive(s) })
            matching.addAll(synergyList.filter { it.firstList.containsCaseInsensitive(s) })
            matching.addAll(synergyList.filter { it.secondList.containsCaseInsensitive(s) })
            matching.addAll(synergyList.filter { it.lastColumn.containsCaseInsensitive(s) })
            _synergies.postValue(matching.toList())
        }

    }


}
