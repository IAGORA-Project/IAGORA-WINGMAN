package com.ssd.iagorawingman.service

class service {

    fun performOnBackgroundThread(runnable: Runnable): Thread? {
        val t: Thread = object : Thread() {
            override fun run() {
                try {
                    runnable.run()
                } finally {
                }
            }
        }
        t.start()
        return t
    }
}