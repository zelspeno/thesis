package org.chemk.thesis.screens.database.accounts.entities

/**
 * Информация о юзере
 */

data class Account(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val surname: String = "",
    val midname: String = "",
    val role: String = "",
    val group: String = "",
    val course: String = "",
    val formEdu: Boolean = true,
    val photoURL: String = ""
)
