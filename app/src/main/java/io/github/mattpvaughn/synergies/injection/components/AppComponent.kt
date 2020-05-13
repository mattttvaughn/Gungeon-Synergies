package io.github.mattpvaughn.synergies.injection.components

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Component
import io.github.mattpvaughn.synergies.application.CustomApplication
import io.github.mattpvaughn.synergies.data.local.PrefsRepo
import io.github.mattpvaughn.synergies.features.library.LibraryViewModelFactory
import io.github.mattpvaughn.synergies.injection.modules.AppModule
import java.io.File
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun applicationContext(): Context
    fun internalFilesDir(): File
    fun externalDeviceDirs(): List<File>
    fun sharedPrefs(): SharedPreferences
    fun prefsRepo(): PrefsRepo
    fun moshi(): Moshi

    // Inject
    fun inject(customApplication: CustomApplication)
}