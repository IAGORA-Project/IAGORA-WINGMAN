include(
    ":app",

    ":core:di",
    ":core:data",
    ":core:domain",
    ":core:util",
    ":core:presentation",

    ":gallery:domain",
    ":gallery:presentation",

    ":market:di",
    ":market:data",
    ":market:domain",
    ":market:presentation",

    ":receive_order:di",
    ":receive_order:data",
    ":receive_order:domain",
    ":receive_order:presentation",

    ":process_order:di",
    ":process_order:data",
    ":process_order:domain",
    ":process_order:presentation",
)

rootProject.buildFileName = "build.gradle.kts"
