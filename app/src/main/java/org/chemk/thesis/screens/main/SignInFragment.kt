package org.chemk.thesis.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentSignInBinding
import org.chemk.thesis.screens.exceptions.*
import org.chemk.thesis.screens.models.Repositories
import org.chemk.thesis.screens.models.SignInViewModel
import org.chemk.thesis.screens.models.viewModelCreator
import org.chemk.thesis.screens.utils.observeEvent

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    private val viewModel by viewModelCreator { SignInViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        binding.enterButton.setOnClickListener {

            val login = binding.login.text.toString()
            val password = binding.password.text.toString()

            viewModel.signIn(login, password)
        }
        binding.forgetPass.setOnClickListener {
            ForgetData().show(childFragmentManager, "ForgetData")
        }

        observeNavigateToTabsEvent()
        observeLaunchAlertDialog()
    }

    private fun observeNavigateToTabsEvent() = viewModel.navigateToTabsEvent.observeEvent(viewLifecycleOwner) {
        findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
    }

    private fun observeLaunchAlertDialog() = viewModel.showAlertDialog.observeEvent(viewLifecycleOwner) {
        WrongData().show(childFragmentManager, "WrongData")
    }
}


