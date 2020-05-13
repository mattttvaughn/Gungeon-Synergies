package io.github.mattpvaughn.synergies.injection.modules

import dagger.Module
import dagger.Provides
import io.github.mattpvaughn.synergies.application.MainActivity
import io.github.mattpvaughn.synergies.injection.scopes.ActivityScope
import io.github.mattpvaughn.synergies.navigation.Navigator

@Module
class ActivityModule(private val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun navigator(): Navigator = Navigator(activity.supportFragmentManager)

}


