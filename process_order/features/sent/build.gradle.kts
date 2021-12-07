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
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Commons.UI))
    implementation(project(BuildModules.Commons.VIEWS))
    implementation(project(BuildModules.ProcessOrder.CORE))


    Dependencies.apply {
        implementation(FRAGMENT_KTX)

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
        implementation(GLIDE)

        // shimmer
        implementation(SHIMMER)

        // remote
        implementation(RETROFIT_GSON)
    }
}