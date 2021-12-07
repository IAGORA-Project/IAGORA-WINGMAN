import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}


dependencies {
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.HELPER))

    Dependencies.apply {
        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)

        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)

        // remote
        implementation(RETROFIT_GSON)

        // common ui
        implementation(RECYCLE_VIEW)
    }
}