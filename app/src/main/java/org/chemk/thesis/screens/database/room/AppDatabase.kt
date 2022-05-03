package org.chemk.thesis.screens.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.chemk.thesis.screens.database.accounts.room.AccountsDao
import org.chemk.thesis.screens.database.accounts.room.entities.AccountDBEntity


@Database(
    version = 2,
    entities = [
        AccountDBEntity::class,
    ]
)

abstract class AppDatabase : RoomDatabase() {

     abstract fun getAccountDao(): AccountsDao
}