package org.chemk.thesis.screens.utils


import android.util.Log
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.chemk.thesis.screens.database.accounts.entities.Account
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule


fun autoFillRandomAccount(): Account {

    initFirebase()

    firebaseAuth = FirebaseAuth.getInstance()
    val emailList = mutableListOf<String>()
    emailList.add(randomEmailAndPassword())
    val email = emailList[0]
    val password = "1234567"
    firebaseAuth.signOut()
//    var id = firebaseAuth.currentUser?.uid
        firebaseAuth.createUserWithEmailAndPassword(email, password)
    Timer().schedule(10000L) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
//            firebaseAuth.currentUser?.reauthenticate(
//                EmailAuthProvider.getCredential(
//                    email,
//                    password
//                )
//            )
    }
    val id = firebaseAuth.uid



    val name = randomFullName()[1]
    val surname = randomFullName()[0]
    val midname = randomFullName()[2]


    val account = Account(
        id = id.toString(),
        email = email,
        name = name,
        surname = surname,
        midname = midname,
        role = "STUDENT",
        group = "IP918",
        course = "4",
        formEdu = true,
    )


    return account
}

fun randomFullName(): List<String> {
    val list = mutableListOf<String>()
    val chars = "abcdefghijklmnopqrstuvwxyz"
        var name = ""
        for (i in 0..6) {
            name += chars.random()
        }
    list.add(0, name+"ov")
    list.add(1, name)
    list.add(2, name+"ovich")
    return list
}

fun randomEmailAndPassword(): String {

    val chars = "abcdefghijklmnopqrstuvwxyz1234567890"
    var key = ""
    for (j in 0..10) {
        key += "${chars.random()}"
    }
    return "$key@gmail.com"
}

fun adminFillData() {

    val account = autoFillRandomAccount()


    val dataMap = mutableMapOf<String, Any>()
    dataMap[CHILD_ID] = account.id
    dataMap[CHILD_EMAIL] = account.email
    dataMap[CHILD_NAME] = account.name
    dataMap[CHILD_SURNAME] = account.surname
    dataMap[CHILD_MIDNAME] = account.midname
    dataMap[CHILD_ROLE] = account.role
    dataMap[CHILD_GROUP] = account.group
    dataMap[CHILD_COURSE] = account.course
    dataMap[CHILD_FORMEDU] = account.formEdu

    REF_DATABASE_ROOT.child(NODE_USERS).child(account.id).updateChildren(dataMap)
}