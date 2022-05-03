package org.chemk.thesis.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import org.chemk.thesis.screens.utils.MutableLiveEvent
import org.chemk.thesis.screens.utils.publishEvent
import org.chemk.thesis.screens.utils.share

/**
 * SplashViewModel проверяет авторизован ли юзер
 */
class SplashViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _launchMainScreenEvent = MutableLiveEvent<Boolean>()
    val launchMainScreenEvent = _launchMainScreenEvent.share()

    init {
        viewModelScope.launch {
            _launchMainScreenEvent.publishEvent(accountsRepository.isSignedIn())
        }
    }
}