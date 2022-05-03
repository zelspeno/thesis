package org.chemk.thesis.screens.database.accounts

import kotlinx.coroutines.flow.Flow
import org.chemk.thesis.screens.database.accounts.entities.Account

/**
 * Репозиторий для взаимодействия с accounts
 */

interface AccountsRepository {

    /**
     * Проверка на авторизацию
     */
    suspend fun isSignedIn(): Boolean

    /**
     *  Авторизация с помощью username и password
     */
    suspend fun signIn(username: String, password: String): Boolean

    /**
     * Выйти из аккаунта
     */
    suspend fun logout()

    /**
     *  Получить всю информацию об аккаунте
     */
    suspend fun getAccountInfo(accountId: Long): Flow<Account?>

    /**
     * Обновить пароль юзера
     */
    suspend fun updateAccountPassword(newPassword: String)

}