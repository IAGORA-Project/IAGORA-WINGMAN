import dependencies.Dependencies

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

dependencies {
    Dependencies.apply {
        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)
    }
}