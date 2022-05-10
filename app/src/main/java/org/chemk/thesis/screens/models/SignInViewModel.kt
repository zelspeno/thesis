package org.chemk.thesis.screens.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.chemk.thesis.R
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import org.chemk.thesis.screens.exceptions.WrongData
import org.chemk.thesis.screens.utils.MutableLiveEvent
import org.chemk.thesis.screens.utils.MutableUnitLiveEvent
import org.chemk.thesis.screens.utils.publishEvent
import org.chemk.thesis.screens.utils.share
import java.lang.Exception

class SignInViewModel(private val accountsRepository: AccountsRepository) : ViewModel() {


    private val _navigateToTabsEvent = MutableUnitLiveEvent()
    val navigateToTabsEvent = _navigateToTabsEvent.share()

    private val _showAlertDialog = MutableLiveEvent<Int>()
    val showAlertDialog = _showAlertDialog.share()

    private lateinit var firebaseAuth: FirebaseAuth


    fun signIn(username: String, password: String) = viewModelScope.launch {
        if (accountsRepository.signIn(username, password)) {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener {
                    launchTabsScreen()
                }
                .addOnFailureListener {
                    launchAlertDialog()
                }
        } else launchAlertDialog()
    }

    private fun launchTabsScreen() = _navigateToTabsEvent.publishEvent()

    private fun launchAlertDialog() = _showAlertDialog.publishEvent(R.string.WrongData)
}