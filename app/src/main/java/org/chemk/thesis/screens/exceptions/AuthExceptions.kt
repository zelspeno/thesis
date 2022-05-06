package org.chemk.thesis.screens.exceptions

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import org.chemk.thesis.R


class WrongData(): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Неверные данные!")
                .setMessage("Данного пользователя не существует, либо введен неверный пароль")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}

class WrongEmail(): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Email не подходит под требования!")
                .setMessage("Введите валидный email адрес")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}

class ForgetData(): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Не можете войти?")
                .setMessage("Обратитесь к администратору в ${getString(R.string.office_number)} кабинет!" +
                        " Либо по номеру: ${getString(R.string.admin_phone)}")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}
