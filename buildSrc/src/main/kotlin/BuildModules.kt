object BuildModules {
    const val APP = ":app"
    const val CORE = ":core"
    const val HELPER = ":helper"

    private const val VIEWMODELS = ":viewmodels"
    private const val FEATURES = ":features"
    private const val MAIN_FEATURES = ":main_features"

    object Commons {
        const val UI = ":commons:ui"
        const val VIEWS = ":commons:views"
    }

    object ProcessOrder {
        private const val NAME = ":process_order"

        const val CORE = "$NAME${BuildModules.CORE}"
        const val HELPER = "$NAME${BuildModules.HELPER}"
        const val VIEWMODELS = "$NAME${BuildModules.VIEWMODELS}"

        object Commons {
            const val UI = "$NAME${BuildModules.Commons.UI}"
            const val VIEWS = "$NAME${BuildModules.Commons.VIEWS}"
        }

        object Features {
            const val MAIN = "$NAME$FEATURES$MAIN_FEATURES"
            const val CONFIRMATION = "$NAME$FEATURES:confirmation"
            const val CONFIRMED = "$NAME$FEATURES:confirmed"
            const val PAYMENT = "$NAME$FEATURES:payment"
            const val PAID = "$NAME$FEATURES:paid"
            const val SENT = "$NAME$FEATURES:sent"
            const val FINISHED = "$NAME$FEATURES:finished"
            const val WAITING_LIST = "$NAME$FEATURES:waiting_list"
        }
    }

    object ReceiveOrder {
        private const val NAME = ":receive_order"

        const val CORE = "$NAME${BuildModules.CORE}"
        const val HELPER = "$NAME${BuildModules.HELPER}"

        object Features {
            const val MAIN = "$NAME$FEATURES$MAIN_FEATURES"
        }
    }

    object Market {
        private const val NAME = ":market"

        const val CORE = "$NAME${BuildModules.CORE}"
        const val HELPER = "$NAME${BuildModules.HELPER}"
        const val VIEWMODELS = "$NAME${BuildModules.VIEWMODELS}"

        object Features {
            const val MAIN = "$NAME$FEATURES$MAIN_FEATURES"
            const val ADD_PRODUCT = "$NAME$FEATURES:add_product"
            const val LIST_PRODUCT = "$NAME$FEATURES:list_product_market"
        }

        object Commons {
            const val VIEWS = "$NAME${BuildModules.Commons.VIEWS}"
        }
    }


}
