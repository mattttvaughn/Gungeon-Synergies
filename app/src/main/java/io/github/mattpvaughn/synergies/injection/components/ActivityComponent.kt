package io.github.mattpvaughn.synergies.injection.components

import dagger.Component
import io.github.mattpvaughn.synergies.features.details.DetailsFragment
import io.github.mattpvaughn.synergies.features.library.LibraryFragment
import io.github.mattpvaughn.synergies.application.MainActivity
import io.github.mattpvaughn.synergies.application.MainActivityViewModelFactory
import io.github.mattpvaughn.synergies.injection.modules.ActivityModule
import io.github.mattpvaughn.synergies.injection.scopes.ActivityScope
import io.github.mattpvaughn.synergies.navigation.Navigator

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun mainActivityViewModelFactory(): MainActivityViewModelFactory
    fun navigator(): Navigator

    fun inject(activity: MainActivity)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(libraryFragment: LibraryFragment)
}

