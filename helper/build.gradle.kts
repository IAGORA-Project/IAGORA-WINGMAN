import dependencies.Dependencies

dependencies {
    implementation("androidx.annotation:annotation:1.3.0")
}
plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}
