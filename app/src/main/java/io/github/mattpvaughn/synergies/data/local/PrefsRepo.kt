package io.github.mattpvaughn.synergies.data.local

import android.content.SharedPreferences
import io.github.mattpvaughn.synergies.data.local.PrefsRepo.Companion.KEY_LAST_REFRESHED_DATA
import javax.inject.Inject

/**
 * An interface for getting/setting persistent preferences for Chronicle
 */
interface PrefsRepo {
    var lastRefreshedDataEpoch: Long
    fun registerListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)
    fun unRegisterListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)

    companion object {
        const val KEY_LAST_REFRESHED_DATA = "last refreshed"

        const val REFRESH_FREQUENCY_MINUTES = 60 * 24
    }
}

/** An implementation of [PrefsRepo] wrapping [SharedPreferences] */
class SharedPreferencesPrefsRepo @Inject constructor(private val sharedPreferences: SharedPreferences) :
    PrefsRepo {

    private val defaultLastRefreshedDataEpoch = 0L
    override var lastRefreshedDataEpoch: Long
        get() = sharedPreferences.getLong(KEY_LAST_REFRESHED_DATA, defaultLastRefreshedDataEpoch)
        set(value) = sharedPreferences.edit().putLong(KEY_LAST_REFRESHED_DATA, value).apply()

    override fun registerListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unRegisterListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}
