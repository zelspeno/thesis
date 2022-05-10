package org.chemk.thesis.screens.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.chemk.thesis.screens.database.accounts.entities.Account

lateinit var firebaseAuth: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: Account

const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_EMAIL = "email"
const val CHILD_NAME = "name"
const val CHILD_SURNAME = "surname"
const val CHILD_MIDNAME = "midname"
const val CHILD_ROLE = "role"
const val CHILD_GROUP = "group"
const val CHILD_COURSE = "course"
const val CHILD_FORMEDU = "formEdu"
const val CHILD_PHOTOURL = "photoURL"

fun initFirebase() {

    firebaseAuth = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}
