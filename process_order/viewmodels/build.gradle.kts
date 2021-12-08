import dependencies.Dependencies

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

dependencies {
    implementation(project(BuildModules.HELPER))
    implementation(project(BuildModules.ProcessOrder.CORE))
    implementation(project(BuildModules.ProcessOrder.HELPER))

    Dependencies.apply {
        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)
    }

}