package com.ssd.iagorawingman.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.ssd.iagorawingman.R

class Loader {
   companion object {
       var progressDialog: Dialog? = null
       fun handleLoading(context: Context) {
           progressDialog = Dialog(context)
           val inflater = LayoutInflater.from(context)
           val dialogLayout = inflater.inflate(R.layout.dialog_loader, null)
           progressDialog?.let {
               it.setContentView(dialogLayout)
               it.setCancelable(false)
               it.window?.setBackgroundDrawableResource(android.R.color.transparent)
           }
       }
   }
}