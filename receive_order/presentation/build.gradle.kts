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
    implementation(project(BuildModules.Core.UTIL))
    implementation(project(BuildModules.Core.PRESENTATION))
    implementation(project(BuildModules.Core.DOMAIN))

    implementation(project(BuildModules.ReceiveOrder.DOMAIN))

    implementation(project(BuildModules.ProcessOrder.Features.MAIN))


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
        implementation(GLIDE)

        // firebase
        implementation(FIREBASE_ANALYTICS)
        implementation(platform(FIREBASE_BOM))
        implementation(FIREBASE_DATABASE)
        implementation(FIREBASE_MESSAGING)

        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)

        // gson
        implementation(RETROFIT_GSON)
    }
}