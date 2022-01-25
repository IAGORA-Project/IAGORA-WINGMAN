import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}


android{
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(BuildModules.Core.UTIL))

    Dependencies.apply {
        // common requirement
        implementation(CORE_KTX)
        implementation(KOTLIN)
        implementation(APPCOMPAT)

        // common ui
        implementation(MATERIAL)
        implementation(NAVIGATION_UI)
        implementation(NAVIGATION_FRAGMENT)
        implementation(CONSTRAIN_LAYOUT)
        implementation(GLIDE)

        // coroutine
        implementation(COROUTINES)
        implementation(COROUTINES_ANDROID)

        // lifecycle
        implementation(LIFECYCLE_SERVICE)
        implementation(LIFECYCLE_RUNTIME)

        // ui
        implementation(RECYCLE_VIEW)
        implementation(LIFECYCLE_VIEWMODEL)

        // di
        implementation(KOIN_ANDROID)
    }
}
