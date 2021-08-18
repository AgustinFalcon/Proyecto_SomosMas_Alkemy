package com.somosmas.somosmas.desingUI

import android.app.Activity
import android.app.AlertDialog
import com.somosmas.somosmas.R

class LoadingDialog(val activity:Activity) {
    private lateinit var isDialog:AlertDialog
    fun startLoading(){

        val inflater=activity.layoutInflater

        val dialogView=inflater.inflate(R.layout.spinner_loadin, null)

         val builder=AlertDialog.Builder(activity)
        builder.setView(dialogView)
         builder.setCancelable(false)
        isDialog=builder.create()
        isDialog.show()
    }

    fun isDissmiss(){
        isDialog.dismiss()
    }
}