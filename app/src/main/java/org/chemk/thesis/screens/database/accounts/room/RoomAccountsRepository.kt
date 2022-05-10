package org.chemk.thesis.screens.database.accounts.room

import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import org.chemk.thesis.screens.database.accounts.entities.Account
import org.chemk.thesis.screens.database.accounts.entities.AccountUpdateEmailTuple
import org.chemk.thesis.screens.exceptions.WrongData
import org.chemk.thesis.screens.settings.AppSettings
import org.chemk.thesis.screens.utils.AsyncLoader
import java.lang.Exception
import java.lang.RuntimeException

class RoomAccountsRepository(
    private val accountsDao: AccountsDao,
    private val appSettings: AppSettings,
    private val ioDispatcher: CoroutineDispatcher
): AccountsRepository {

    private lateinit var firebaseAuth: FirebaseAuth

    private val currentAccountIdFlow = AsyncLoader {
        MutableStateFlow(AccountId(appSettings.getCurrentAccountId()))
    }

    override suspend fun isSignedIn(): Boolean {

        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        return firebaseUser != null

    }

    override suspend fun signIn(username: String, password: String): Boolean =
        (!(username.isBlank() || password.isBlank()))

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun getAccountInfo(accountId: Long): Flow<Account?> =
        accountsDao.getById(accountId).map {
            it -> it?.toAccount()
        }

    override suspend fun updateAccountPassword(newPassword: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateAccountEmail(newEmail: String) {
        val accountId = appSettings.getCurrentAccountId()
        updateAccountEmailById(accountId, newEmail)
    }

    private suspend fun updateAccountEmailById(accountId: Long, newEmail: String) =
        accountsDao.updateEmail(AccountUpdateEmailTuple(accountId, newEmail))



    private suspend fun findAccountIdByUsernameAndPassword(username: String, password: String): Long? {
        val tuple = accountsDao.findByUsername(username) ?: return null
        if (tuple.password != password) {
            return null
        } else return tuple.id

    }

    override suspend fun getAccount(): Flow<Account?> {
        return currentAccountIdFlow.get()
            .flatMapLatest { accountId ->
                if (accountId.value == AppSettings.NO_ACCOUNT_ID) {
                    flowOf(null)
                } else {
                    getAccountInfo(accountId.value)
                }
            }
            .flowOn(ioDispatcher)
    }

    private class AccountId(val value: Long)
}