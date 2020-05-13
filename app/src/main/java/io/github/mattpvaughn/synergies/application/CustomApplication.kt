package io.github.mattpvaughn.synergies.application

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import io.github.mattpvaughn.synergies.BuildConfig
import io.github.mattpvaughn.synergies.injection.components.AppComponent
import io.github.mattpvaughn.synergies.injection.components.DaggerAppComponent
import io.github.mattpvaughn.synergies.injection.modules.AppModule
import javax.inject.Singleton


@Singleton
open class CustomApplication : Application() {
    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent by lazy {
        initializeComponent()
    }

    init {
        INSTANCE = this
    }

    companion object {
        private var INSTANCE: CustomApplication? = null

        @JvmStatic
        fun get(): CustomApplication = INSTANCE!!
    }

    override fun onCreate() {
        if (STRICT_MODE && BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
//                    choose which ones you want
//                    .detectDiskReads()
//                    .detectDiskWrites()
//                    .detectNetwork() // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
            StrictMode.setVmPolicy(
                VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .detectActivityLeaks()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }

        appComponent.inject(this)
        super.onCreate()
    }

    open fun initializeComponent(): AppComponent {
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}
