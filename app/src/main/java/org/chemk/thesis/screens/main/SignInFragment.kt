package org.chemk.thesis.screens.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        val login = binding.login
        val password = binding.password

        with(binding) {
            enterButton.setOnClickListener {
                if (login.text.toString() == "admin" && password.text.toString() == "admin") {
                    findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
                } else WrongData().show(childFragmentManager, "WrongData")
            }
            forgetPass.setOnClickListener {
                ForgetData().show(childFragmentManager, "ForgetData")
            }
        }
    }
}

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
