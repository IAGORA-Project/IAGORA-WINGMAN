import extensions.applyDefault

plugins.apply {
    with(BuildPlugins) {
        UPDATE_DEPENDENCIES
        DETEKT
        DOKKA
        KTLINT
        SPOTLESS
    }
}

allprojects {
    repositories.applyDefault()
}

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}