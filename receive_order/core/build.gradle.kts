import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}


dependencies {
    implementation(project(BuildModules.Core.UTIL))
    implementation(project(BuildModules.Core.DATA))
    implementation(project(BuildModules.Core.DOMAIN))
    implementation(project(BuildModules.HELPER))
    implementation(project(BuildModules.ReceiveOrder.HELPER))
    Dependencies.apply {
        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)

        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)

        // common ui
        implementation(RECYCLE_VIEW)

        // remote
        implementation(RETROFIT_GSON)
    }
}