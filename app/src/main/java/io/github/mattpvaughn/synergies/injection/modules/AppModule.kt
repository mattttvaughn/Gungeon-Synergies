package io.github.mattpvaughn.synergies.injection.modules

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.ContextCompat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.github.mattpvaughn.synergies.application.APP_NAME
import io.github.mattpvaughn.synergies.data.local.PrefsRepo
import io.github.mattpvaughn.synergies.data.local.SharedPreferencesPrefsRepo
import java.io.File
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        app.getSharedPreferences(APP_NAME, MODE_PRIVATE)

    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    @Provides
    @Singleton
    fun providePrefsRepo(prefsImpl: SharedPreferencesPrefsRepo): PrefsRepo = prefsImpl

    @Provides
    @Singleton
    fun provideInternalDeviceDirs(): File = app.applicationContext.filesDir

    @Provides
    @Singleton
    fun provideExternalDeviceDirs(): List<File> =
        ContextCompat.getExternalFilesDirs(app.applicationContext, null).toList()
}