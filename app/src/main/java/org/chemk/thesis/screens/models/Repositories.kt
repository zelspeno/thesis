package org.chemk.thesis.screens.models

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import org.chemk.thesis.screens.database.accounts.room.RoomAccountsRepository
import org.chemk.thesis.screens.database.room.AppDatabase
import org.chemk.thesis.screens.settings.AppSettings
import org.chemk.thesis.screens.settings.SharedPreferencesAppSettings

object Repositories {

    private lateinit var applicationContext: Context

    private val database: AppDatabase by lazy<AppDatabase> {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .createFromAsset("initial_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(applicationContext)
    }

    val accountsRepository: AccountsRepository by lazy {
        RoomAccountsRepository(database.getAccountDao(), appSettings, ioDispatcher)
    }

    fun init(context: Context) {
        applicationContext = context
    }

}
