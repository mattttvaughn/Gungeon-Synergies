package io.github.mattpvaughn.synergies.application

import io.github.mattpvaughn.synergies.injection.components.AppComponent

class Injector private constructor() {
    companion object {
        fun get() : AppComponent = CustomApplication.get().appComponent
    }
}