package org.chemk.thesis.screens.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import org.chemk.thesis.screens.home.WeekSchedule
import org.chemk.thesis.screens.journal.GroupLessons
import org.chemk.thesis.screens.journal.Lessons
import org.chemk.thesis.screens.journal.MarkForDay
import org.chemk.thesis.screens.journal.StudentMarks
import org.chemk.thesis.screens.main.HomeFragment
import org.chemk.thesis.screens.utils.*
import java.io.File
import java.io.FileInputStream
import kotlin.random.Random

class JournalViewModel(private val accountsRepository: AccountsRepository) : ViewModel() {

    val groups = HomeFragment().getGroupsWithRc()
    val lessons = Lessons().getValuesList()

    val listOfSubjects = mutableListOf<String>()

    fun getSubjectsByGroup(group: String) : LiveData<GroupLessons> {

        val result = MutableLiveData<GroupLessons>()

        initFirebase()

        GROUP_LESSONS = GroupLessons(group, mutableMapOf())

        var groupLessons = GroupLessons(group, mutableMapOf())

        viewModelScope.launch {

            REF_DATABASE_ROOT.child(NODE_GROUPS).child(group)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    groupLessons = GroupLessons(group, it.value as Map<String, Boolean>)
                    Log.d("it", "${it.value}")
                    Log.d("GROUP_LESSONS", "${groupLessons.lessons}")

//                    for (i in groupLessons.lessons) {
//                        if (i.value) {
//                            listOfSubjects += i.key
//                        }
//                    }
                    result.postValue(groupLessons)
                })
        }
        return result
    }


    fun fillDatabase() {

        initFirebase()

        var dataMap = mutableMapOf<String, Boolean>()


        for (i in groups.keys) {

            dataMap = fillDataMap(dataMap)
            Log.d("dataMap", "$dataMap")

            REF_DATABASE_ROOT.child(NODE_GROUPS).child(i).updateChildren(dataMap as Map<String, Any>)
        }



    }

    private fun fillDataMap(dataMap: MutableMap<String, Boolean>) : MutableMap<String, Boolean> {

        Log.d("lessons", "$lessons")

        for (j in lessons) {
            dataMap[j] = Random.nextBoolean()
        }
        return dataMap
    }

    fun getMarks(subject: String, group: String, name: String) : LiveData<StudentMarks> {

        val result = MutableLiveData<StudentMarks>()

        viewModelScope.launch {

            val storageRef = Firebase.storage.reference
            val rasp = storageRef.child("journal/$subject.xlsx")
            val localFile = File.createTempFile("marks", "xlsx")
            rasp.getFile(localFile).addOnSuccessListener {
                var studentMarks = StudentMarks()
                val listMarks = mutableListOf<MarkForDay>()
                val file = FileInputStream(localFile)
                val workbook = XSSFWorkbook(file)
                var sheet = workbook.getSheet(group)
                listMarks += MarkForDay(0, "")
                for (i in 2..6) {
                    if (sheet.getRow(i).getCell(0).toString() == name) {
                        studentMarks.name = name
                        studentMarks.group = group
                        for (j in 1..31) {
                            var mark = sheet.getRow(i).getCell(j) ?: ""
                            listMarks += MarkForDay(day = j, mark.toString())
                        }
                    }
                }
                result.postValue(StudentMarks(group = group, name = name, marks = listMarks))
                file.close()
            }
        }
        return result
    }
}