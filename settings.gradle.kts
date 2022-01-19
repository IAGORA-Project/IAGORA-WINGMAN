include(
    ":app",

    ":helper",

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

    ":process_order:core",
    ":process_order:helper",
    ":process_order:viewmodels",
    ":process_order:commons:views",
    ":process_order:features:sent",
    ":process_order:features:paid",
    ":process_order:features:payment",
    ":process_order:features:finished",
    ":process_order:features:confirmed",
    ":process_order:features:confirmation",
    ":process_order:features:waiting_list",
    ":process_order:features:main_features",
)

rootProject.buildFileName = "build.gradle.kts"
