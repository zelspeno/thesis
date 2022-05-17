package org.chemk.thesis.screens.exceptions

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class WrongJournal(): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Неверные данные!")
                .setMessage("Выберите период и предмет")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}