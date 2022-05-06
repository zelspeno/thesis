package org.chemk.thesis.screens.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.chemk.thesis.R
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import org.chemk.thesis.screens.database.accounts.entities.Account
import org.chemk.thesis.screens.models.Repositories.accountsRepository
import org.chemk.thesis.screens.utils.MutableLiveEvent
import org.chemk.thesis.screens.utils.MutableUnitLiveEvent
import org.chemk.thesis.screens.utils.publishEvent
import org.chemk.thesis.screens.utils.share

class ProfileViewModel(private val accountsRepository: AccountsRepository): ViewModel() {

    private val _showAlertDialog = MutableLiveEvent<Int>()
    val showAlertDialog = _showAlertDialog.share()

    private val _account = MutableLiveData<Account>()
    val account = _account.share()

    private val _restartFromLoginEvent = MutableLiveEvent<Unit>()
    val restartWithSignInEvent = _restartFromLoginEvent.share()

    init {
        viewModelScope.launch {
            accountsRepository.getAccount().collect {
                _account.value = it
            }
        }
    }

    fun updateAccountEmail(newEmail: String) {
        val emailPattern =
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        val email = newEmail.trim()
        if (email.matches(emailPattern.toRegex())) {
            viewModelScope.launch {
                accountsRepository.updateAccountEmail(newEmail)
            }
        } else launchAlertDialog()
    }

    fun logout() {
        viewModelScope.launch {
            accountsRepository.logout()
            restartAppFromLoginScreen()
        }
    }

    private fun restartAppFromLoginScreen() {
        _restartFromLoginEvent.publishEvent()
    }

    private fun launchAlertDialog() = _showAlertDialog.publishEvent(R.string.WrongData)
}

