include(
    ":app",
    ":core",
    ":helper",
    ":commons:ui",
    ":commons:views",
    ":receive_order:core",
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
