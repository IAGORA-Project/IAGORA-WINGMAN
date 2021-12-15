import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
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

    implementation(project(BuildModules.ReceiveOrder.CORE))
    implementation(project(BuildModules.ReceiveOrder.HELPER))

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

        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)


        // firebase
        implementation(FIREBASE_ANALYTICS)
        implementation(platform(FIREBASE_BOM))
        implementation(FIREBASE_DATABASE)
        implementation(FIREBASE_MESSAGING)

        // gson
        implementation(RETROFIT_GSON)
    }
}