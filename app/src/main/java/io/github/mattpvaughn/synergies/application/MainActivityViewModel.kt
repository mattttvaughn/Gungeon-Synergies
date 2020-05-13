package io.github.mattpvaughn.synergies.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.mattpvaughn.synergies.util.Event
import io.github.mattpvaughn.synergies.util.postEvent
import javax.inject.Inject


class MainActivityViewModel @Inject constructor() : ViewModel() {

    private var _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    fun showUserMessage(errorMessage: String) {
        _errorMessage.postEvent(errorMessage)
    }
}

