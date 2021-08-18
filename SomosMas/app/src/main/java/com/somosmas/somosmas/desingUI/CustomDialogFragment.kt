package com.somosmas.somosmas.desingUI

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.somosmas.somosmas.R
import java.lang.IllegalStateException

class CustomDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            var alertDialog = AlertDialog.Builder(it)
            alertDialog.setView(
                requireActivity().layoutInflater.inflate(
                    R.layout.fragment_custom_dialog,
                    null
                )
            )
            alertDialog.setPositiveButton("ok", DialogInterface.OnClickListener { dialog, id ->
                dismiss()
            })

            alertDialog.create()
        } ?: throw IllegalStateException("activity is null")
    }
}