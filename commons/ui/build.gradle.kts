import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

android{
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    Dependencies.apply {
        // common requirement
        implementation(CORE_KTX)
        implementation(KOTLIN)
        implementation(APPCOMPAT)

        // coroutine
        implementation(COROUTINES)
        implementation(COROUTINES_ANDROID)

        // lifecycle
        implementation(LIFECYCLE_SERVICE)
        implementation(LIFECYCLE_RUNTIME)

        // ui
        implementation(RECYCLE_VIEW)
        implementation(LIFECYCLE_VIEWMODEL)

        //dependencies

        implementation(KOIN_CORE)
        implementation(KOIN_ANDROID)
    }
}
