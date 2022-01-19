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
    implementation(project(BuildModules.Core.DOMAIN))
    implementation(project(BuildModules.ProcessOrder.HELPER))
    implementation(project(BuildModules.Core.PRESENTATION))
    implementation(project(BuildModules.ProcessOrder.Commons.VIEWS))


    Dependencies.apply {
        implementation(APPCOMPAT)
        implementation(FRAGMENT_KTX)
        implementation(ANDROID_LEGACY)

        // common ui
        implementation(MATERIAL)
        implementation(CONSTRAIN_LAYOUT)
        implementation(RECYCLE_VIEW)

        // shimmer
        implementation(SHIMMER)

    }
}