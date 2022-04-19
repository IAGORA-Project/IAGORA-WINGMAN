import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(BuildModules.Core.DATA))
    implementation(project(BuildModules.Core.DOMAIN))
    implementation(project(BuildModules.Core.DI))
    implementation(project(BuildModules.Core.UTIL))
    implementation(project(BuildModules.Core.PRESENTATION))

    implementation(project(BuildModules.Market.DOMAIN))
    implementation(project(BuildModules.Gallery.PRESENTATION))

    Dependencies.apply {
        implementation(APPCOMPAT)
        implementation(FRAGMENT_KTX)
        implementation(ANDROID_LEGACY)

        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)

        // common ui
        implementation(MATERIAL)
        implementation(NAVIGATION_UI)
        implementation(NAVIGATION_FRAGMENT)
        implementation(LIFECYCLE_SERVICE)
        implementation(LIFECYCLE_RUNTIME)
        implementation(CONSTRAIN_LAYOUT)
        implementation(RECYCLE_VIEW)

        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)
    }
}