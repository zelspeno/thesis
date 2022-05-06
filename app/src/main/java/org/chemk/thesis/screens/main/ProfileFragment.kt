package org.chemk.thesis.screens.main

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentProfileBinding
import org.chemk.thesis.screens.exceptions.WrongData
import org.chemk.thesis.screens.exceptions.WrongEmail
import org.chemk.thesis.screens.models.ProfileViewModel
import org.chemk.thesis.screens.models.Repositories
import org.chemk.thesis.screens.models.SignInViewModel
import org.chemk.thesis.screens.models.viewModelCreator
import org.chemk.thesis.screens.utils.findTopNavController
import org.chemk.thesis.screens.utils.observeEvent

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModelCreator { ProfileViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        fillData()

        binding.editProfileButton.setOnClickListener {
            binding.editProfileButton.visibility = View.GONE
            binding.profileEmailEdit.isEnabled = true
            binding.saveProfileButton.visibility = View.VISIBLE
        }

        binding.saveProfileButton.setOnClickListener {
            viewModel.updateAccountEmail(binding.profileEmailEdit.text.toString())
            binding.editProfileButton.visibility = View.VISIBLE
            binding.profileEmailEdit.isEnabled = false
            binding.saveProfileButton.visibility = View.GONE

        }

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }

        observeRestartAppFromLoginScreenEvent()
        observeLaunchAlertDialog()


    }

    private fun fillData() {

        viewModel.account.observe(viewLifecycleOwner) { account ->
            if (account == null) return@observe
            ("${account.surname} ${account.name} \n ${account.midname}").also {
                binding.profileFullName.text = it
            }
            (binding.profileGroupEdit as TextView).text = account.group
            (binding.profileCourseEdit as TextView).text = account.course
            (binding.profileStatusEdit as TextView).text = account.role
            (binding.profileEmailEdit as TextView).text = account.email
            if (account.formEdu) {
                (binding.profileFormEduEdit as TextView).text = "Очная"
            } else (binding.profileFormEduEdit as TextView).text = "Заочная"

        }
    }

    private fun observeRestartAppFromLoginScreenEvent() {
        viewModel.restartWithSignInEvent.observeEvent(viewLifecycleOwner) {
            findTopNavController().navigate(R.id.signInFragment, null, navOptions {
                popUpTo(R.id.tabsFragment) {
                    inclusive = true
                }
            })
        }
    }

    private fun observeLaunchAlertDialog() = viewModel.showAlertDialog.observeEvent(viewLifecycleOwner) {
        WrongEmail().show(childFragmentManager, "WrongEmail")
    }
}