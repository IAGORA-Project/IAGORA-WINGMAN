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
    implementation(project(BuildModules.HELPER))

    implementation(project(BuildModules.Commons.UI))
    implementation(project(BuildModules.Commons.VIEWS))

    implementation(project(BuildModules.ProcessOrder.HELPER))
    implementation(project(BuildModules.ProcessOrder.VIEWMODELS))

    implementation(project(BuildModules.ProcessOrder.Commons.VIEWS))


    Dependencies.apply {
        implementation(APPCOMPAT)
        implementation(FRAGMENT_KTX)


        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)

        // common ui
        implementation(MATERIAL)
        implementation(NAVIGATION_UI)
        implementation(NAVIGATION_FRAGMENT)
        implementation(CONSTRAIN_LAYOUT)
        implementation(RECYCLE_VIEW)

        // shimmer
        implementation(SHIMMER)
    }
}