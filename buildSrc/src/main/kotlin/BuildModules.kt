object BuildModules {
    const val APP = ":app"
    const val CORE = ":core"
    const val HELPER = ":helper"

    object ProcessOrder {
        const val CORE = ":process_order:core"
        const val HELPER = ":process_order:helper"
        const val VIEWMODELS = ":process_order:viewmodels"

        object Commons {
            const val UI = ":process_order:commons:ui"
            const val VIEWS = ":process_order:commons:views"
        }

        object Features {
            const val MAIN = ":process_order:features:main_features"
            const val CONFIRMATION = ":process_order:features:confirmation"
            const val CONFIRMED = ":process_order:features:confirmed"
            const val PAYMENT = ":process_order:features:payment"
            const val PAID = ":process_order:features:paid"
            const val SENT = ":process_order:features:sent"
            const val FINISHED = ":process_order:features:finished"
            const val WAITING_LIST = ":process_order:features:waiting_list"
        }
    }

    object ReceiveOrder {
        const val CORE = ":receive_order:core"
        const val MAIN = ":receive_order:main_features"
    }

    object Commons {
        const val UI = ":commons:ui"
        const val VIEWS = ":commons:views"
    }
}
