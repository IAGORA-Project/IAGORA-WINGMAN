package com.iagora.wingman.app.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BroadcastService: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        println("SKSJKSJSKLJSKLSJS $intent")
    }
}