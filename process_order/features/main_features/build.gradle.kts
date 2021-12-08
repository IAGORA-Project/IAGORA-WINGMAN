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
    implementation(project(BuildModules.ProcessOrder.CORE))

    implementation(project(BuildModules.ProcessOrder.Features.WAITING_LIST))
    implementation(project(BuildModules.ProcessOrder.Features.SENT))
    implementation(project(BuildModules.ProcessOrder.Features.PAID))
    implementation(project(BuildModules.ProcessOrder.Features.PAYMENT))
    implementation(project(BuildModules.ProcessOrder.Features.FINISHED))
    implementation(project(BuildModules.ProcessOrder.Features.CONFIRMED))
    implementation(project(BuildModules.ProcessOrder.Features.CONFIRMATION))

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
    }
}