package org.chemk.thesis.screens.database.accounts.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.chemk.thesis.screens.database.accounts.entities.AccountFullnameTuple
import org.chemk.thesis.screens.database.accounts.entities.AccountSignInTuple
import org.chemk.thesis.screens.database.accounts.entities.AccountUpdateEmailTuple
import org.chemk.thesis.screens.database.accounts.room.entities.AccountDBEntity

@Dao
interface AccountsDao {

    @Query("SELECT name, surname, midname FROM accounts WHERE id = :accountId")
    suspend fun getFullNameById(accountId: Long): AccountFullnameTuple

    @Query("SELECT `group` FROM accounts WHERE id = :accountId")
    suspend fun getGroupById(accountId: Long): String

    @Query("SELECT * FROM accounts WHERE id = :accountId")
    fun getById(accountId: Long): Flow<AccountDBEntity?>

    @Update(entity = AccountDBEntity::class)
    suspend fun updateEmail(updateEmailTuple: AccountUpdateEmailTuple)

    @Query("SELECT id, password FROM accounts WHERE username = :username")
    suspend fun findByUsername(username: String): AccountSignInTuple

}