import dependencies.Dependencies

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

dependencies {
    implementation(project(BuildModules.HELPER))


    implementation(project(BuildModules.Core.PRESENTATION))

    Dependencies.apply {
        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)
    }

}