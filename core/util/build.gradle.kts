import dependencies.Dependencies
import extensions.buildConfigStringField
import extensions.getLocalProperty
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}




dependencies {
    Dependencies.apply {
        // requirement
        implementation(CORE_KTX)
        implementation(KOIN_ANDROID)

        // remote
        implementation(RETROFIT_GSON)
    }
}