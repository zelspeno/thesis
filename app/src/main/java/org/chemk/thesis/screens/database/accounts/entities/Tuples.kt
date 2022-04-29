package org.chemk.thesis.screens.database.accounts.entities

data class AccountSignInTuple(
    val id: Long,
    val password: String
)

data class AccountUpdateEmailTuple(
    val id: Long,
    val email: String
)

data class AccountFullnameTuple(
    val name: String,
    val surname: String,
    val midname: String
)

