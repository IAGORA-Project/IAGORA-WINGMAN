import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}


dependencies {
    implementation(project(BuildModules.Core.UTIL))
    implementation(project(BuildModules.Core.DOMAIN))

    Dependencies.apply {
        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)


        // remote
        implementation(RETROFIT_GSON)
    }
}