object BuildModules {
    const val APP = ":app"
    const val HELPER = ":helper"

    private const val PRESENTATION = ":presentation"
    private const val DOMAIN = ":domain"
    private const val DATA = ":data"
    private const val DI = ":di"
    private const val UTIL = ":util"


    object Core {
        private const val NAME = ":core"

        const val PRESENTATION = "$NAME${BuildModules.PRESENTATION}"
        const val DOMAIN = "$NAME${BuildModules.DOMAIN}"
        const val DATA = "$NAME${BuildModules.DATA}"
        const val DI = "$NAME${BuildModules.DI}"
        const val UTIL = "$NAME${BuildModules.UTIL}"
    }


    object ProcessOrder {
        private const val NAME = ":process_order"

        const val PRESENTATION = "$NAME${BuildModules.PRESENTATION}"
        const val DOMAIN = "$NAME${BuildModules.DOMAIN}"
        const val DATA = "$NAME${BuildModules.DATA}"
        const val DI = "$NAME${BuildModules.DI}"
    }

    object ReceiveOrder {
        private const val NAME = ":receive_order"

        const val PRESENTATION = "$NAME${BuildModules.PRESENTATION}"
        const val DOMAIN = "$NAME${BuildModules.DOMAIN}"
        const val DATA = "$NAME${BuildModules.DATA}"
        const val DI = "$NAME${BuildModules.DI}"
    }

    object Market {
        private const val NAME = ":market"

        const val PRESENTATION = "$NAME${BuildModules.PRESENTATION}"
        const val DOMAIN = "$NAME${BuildModules.DOMAIN}"
        const val DATA = "$NAME${BuildModules.DATA}"
        const val DI = "$NAME${BuildModules.DI}"
    }

    object Gallery {
        private const val NAME = ":gallery"

        const val PRESENTATION = "$NAME${BuildModules.PRESENTATION}"
        const val DOMAIN = "$NAME${BuildModules.DOMAIN}"
    }


}
