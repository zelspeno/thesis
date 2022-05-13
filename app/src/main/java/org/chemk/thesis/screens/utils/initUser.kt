package org.chemk.thesis.screens.utils

import org.chemk.thesis.screens.database.accounts.entities.Account

fun initUser() {
    initFirebase()
    val uid = firebaseAuth.currentUser?.uid.toString()
    REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(Account::class.java) ?: Account()
        })
}