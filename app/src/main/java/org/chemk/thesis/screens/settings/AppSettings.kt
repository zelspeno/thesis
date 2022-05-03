package org.chemk.thesis.screens.settings

interface AppSettings {

    /**
     * Получить текущий идентификатор входа в аккаунт пользователем
     */
    fun getCurrentAccountId(): Long

    /**
     * Поставить идентификатор входа в аккаунт пользователем
     */
    fun setCurrentAccountId(accountId: Long)

    companion object {

        /**
         *  Отобразить, что пользователь не зашел в аккаунт
         */
        const val NO_ACCOUNT_ID: Long = 1
    }
}