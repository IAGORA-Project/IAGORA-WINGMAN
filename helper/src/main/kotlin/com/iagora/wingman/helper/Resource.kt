package com.iagora.wingman.helper

class Resource<out T>(val status: Status?, val data: T?, val message: String?) {
        companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(msg: String, data: T?): Resource<T> {
            return Resource(Status.LOADING, data, msg)
        }
    }

}

 fun <T> Resource<T>.set( error: () -> Unit, success: () -> Unit, loading: () -> Unit) {
    when (status) {
        Status.ERROR -> error()

        Status.LOADING -> loading()


        Status.SUCCESS -> success()

        null -> {
            /*NO-ACTION*/
        }
    }

}




enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}