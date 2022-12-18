package com.torresj.newathletic.utils

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import com.torresj.newathletic.R
import pl.bclogic.pulsator4droid.library.PulsatorLayout

object ProgressBarGenerico {
    private var progressDialog: ProgressDialog? = null


    fun LoadProgress(context: Context?) {

        progressDialog = ProgressDialog(context)
        progressDialog!!.setCanceledOnTouchOutside(false)
        progressDialog!!.show()
        progressDialog!!.setCancelable(false)
        progressDialog!!.setContentView(R.layout.progress_dialog)
        progressDialog!!.window!!.setBackgroundDrawableResource(R.color.transparent)
        val pulsator: PulsatorLayout =
            progressDialog!!.findViewById<View>(R.id.pulsator) as PulsatorLayout
        pulsator.start()
    }

    fun HideProgreess() {
        if (progressDialog != null) progressDialog!!.dismiss()
    }
}