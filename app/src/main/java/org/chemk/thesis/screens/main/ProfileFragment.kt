package org.chemk.thesis.screens.main

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentProfileBinding
import org.chemk.thesis.screens.database.accounts.entities.Account
import org.chemk.thesis.screens.exceptions.WrongData
import org.chemk.thesis.screens.exceptions.WrongEmail
import org.chemk.thesis.screens.models.ProfileViewModel
import org.chemk.thesis.screens.models.Repositories
import org.chemk.thesis.screens.models.SignInViewModel
import org.chemk.thesis.screens.models.viewModelCreator
import org.chemk.thesis.screens.utils.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private val viewModel by viewModelCreator { ProfileViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        firebaseAuth = FirebaseAuth.getInstance()

        fillData()

        Log.d("Firebase auth", "${firebaseAuth.uid}")

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }

        observeRestartAppFromLoginScreenEvent()
        observeLaunchAlertDialog()


    }

    private fun fillData() {

        initFirebase()

        val uid = firebaseAuth.currentUser?.uid


        if (!(uid.isNullOrEmpty())) {

            "${USER.surname} ${USER.name} \n ${USER.midname}".also { binding.profileFullName.text = it }
            (binding.profileGroupEdit as TextView).text = USER.group
            (binding.profileCourseEdit as TextView).text = USER.course
            (binding.profileStatusEdit as TextView).text = USER.role
            (binding.profileEmailEdit as TextView).text = USER.email
            val photo = USER.photoURL.toUri()
            Log.d("fillData", "${USER.email}")
            binding.profileImage.maxHeight = 200
            binding.profileImage.maxWidth = 200
            Picasso.get().load(photo).resize(200,200).into(binding.profileImage)
            if (USER.formEdu) {
                (binding.profileFormEduEdit as TextView).text = "Очная"
            } else (binding.profileFormEduEdit as TextView).text = "Заочная"
        }
        else Log.d("FillData", "Error, $uid")
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