package io.github.mattpvaughn.synergies.features.details

import androidx.lifecycle.*
import io.github.mattpvaughn.synergies.data.local.model.Synergy
import io.github.mattpvaughn.synergies.util.Event
import io.github.mattpvaughn.synergies.util.postEvent

class DetailsViewModel(synergy: Synergy) : ViewModel() {

    private var _messageForUser = MutableLiveData<Event<String>>()
    val messageForUser: LiveData<Event<String>>
        get() = _messageForUser

    private var _gradientLiveData = MutableLiveData<Synergy>(synergy)
    val gradientLiveData: LiveData<Synergy>
        get() = _gradientLiveData

    private fun showUserMessage(message: String) {
        _messageForUser.postEvent(message)
    }

}
