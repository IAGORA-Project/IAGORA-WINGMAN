import dependencies.Dependencies

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

dependencies {
    implementation(project(BuildModules.HELPER))
    implementation(project(BuildModules.Market.CORE))
    implementation(project(BuildModules.Market.HELPER))
    implementation(project(BuildModules.Commons.UI))

    Dependencies.apply {
        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)
    }

}