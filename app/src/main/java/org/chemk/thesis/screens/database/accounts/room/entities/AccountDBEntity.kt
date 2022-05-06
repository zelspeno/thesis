package org.chemk.thesis.screens.database.accounts.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.chemk.thesis.screens.database.accounts.entities.Account

@Entity(tableName = "accounts",
    indices = [
        Index("email", unique = true),
        Index("username", unique = true)
    ]
)

data class AccountDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val username: String,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val midname: String,
    val role: String,
    val group: String,
    val course: String,
    @ColumnInfo(name = "form_edu") val formEdu: Boolean
) {

    fun toAccount(): Account = Account(
        id = id,
        username = username,
        email = email,
        name = name,
        surname = surname,
        midname = midname,
        role = role,
        group = group,
        course = course,
        formEdu = formEdu,
    )

}