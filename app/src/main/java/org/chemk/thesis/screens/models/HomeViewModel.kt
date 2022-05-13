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
import org.chemk.thesis.screens.home.Schedule
import org.chemk.thesis.screens.home.WeekSchedule
import org.chemk.thesis.screens.utils.MutableLiveEvent
import org.chemk.thesis.screens.utils.share
import java.io.File
import java.io.FileInputStream

class HomeViewModel(private val accountsRepository: AccountsRepository) : ViewModel() {

    var sheetName123: String = ""

    var weekSchedule: WeekSchedule = WeekSchedule()


//    private val _getSchedule = MutableLiveEvent<Unit>()
//    val getSchedule = _getSchedule.share()

//    private val _groupsWRC = MutableLiveData<>()
//    val groupsWRC = _groupsWRC.share()

    var groups = mutableMapOf<String, String>()


    fun getRowCellByIndexes(group: String, dayOfWeek: String) {
        Log.d("grcbi", group)
        val pair = getRowCellByGroupIndex(group)
        val iRow = pair.first
        val iCell = pair.second
        Log.d("IROW ICELL", "$iRow, $iCell")
        val storageRef = Firebase.storage.reference
        val rasp = storageRef.child("raspisanie/rasp-2sem2022-4korp.xlsx")
        val localFile = File.createTempFile("rasp-2sem2022-4korp.xlsx", "xlsx")
        rasp.getFile(localFile).addOnSuccessListener {
            val file = FileInputStream(localFile)
            val workbook = XSSFWorkbook(file)
            val sheetName = workbook.getSheetAt(0).getRow(iRow).getCell(iCell)
            closeScheduleFile(file)
            Log.d("getSchedule", sheetName.toString())
            sheetName123 = sheetName.toString()
        }
    }

    fun getRowCell(group: String, dayOfWeek: String) {
        val pair = getRowCellByGroupIndex(group) // (8;26)
        val iRow = pair.first // 8
        val iCell = pair.second // 26
        Log.d("IROW ICELL", "$iRow, $iCell")
        val storageRef = Firebase.storage.reference
        val rasp = storageRef.child("raspisanie/rasp-2sem2022-4korp.xlsx")
        val localFile = File.createTempFile("rasp-2sem2022-4korp.xlsx", "xlsx")
        rasp.getFile(localFile).addOnSuccessListener {
            val file = FileInputStream(localFile)
            val workbook = XSSFWorkbook(file)
            val sheetName = workbook.getSheetAt(0).getRow(iRow).getCell(iCell)
            closeScheduleFile(file)
            Log.d("getSchedule", sheetName.toString())
        }
    }

    fun getRowCellByGroupIndex(group: String): Pair<Int, Int> {

        var row = ""
        var cell = ""

        for (i in group) {
            if (i.isDigit())  row+=i
            else cell +="$i"
        }

        var nCell = 0
        val cellCh = cell.toCharArray()
        if (cellCh.size != 1) {
            var count = 0
            for (c in cellCh) {
                val temp = c.code
                val tempInt = 64
                if (temp in 65..90) {
                    if (count == 0) {
                        nCell += temp - tempInt
                        count++
                    } else nCell += temp - tempInt + 24
                }
            }
        }
        if (cellCh.size == 1) {
            nCell += cellCh[0].code - 64
        }

        return Pair(row.toInt()-1, nCell)
    }

    fun getSchedule(group: String, dayOfWeek: String) {

        val storageRef = Firebase.storage.reference
        val rasp = storageRef.child("raspisanie/rasp-2sem2022-4korp.xlsx")
        val localFile = File.createTempFile("rasp-2sem2022-4korp.xlsx", "xlsx")
        rasp.getFile(localFile).addOnSuccessListener {
            val file = FileInputStream(localFile)
            val workbook = XSSFWorkbook(file)
            val sheetName = workbook.getSheetAt(0).sheetName
            closeScheduleFile(file)
            Log.d("getSchedule", sheetName)
        }
    }

    private fun closeScheduleFile(file: FileInputStream?) {
        file?.close()
    }

    /**
     * Нужен RC код в group, к примеру 'AA9'
     */
    fun fillWeekSchedule(group: String): LiveData<WeekSchedule> {

        val result = MutableLiveData<WeekSchedule>()

        viewModelScope.launch {

            val pair = getRowCellByGroupIndex(group) // (8;26)
            val iRow = pair.first // 8
            val iCell = pair.second // 26
            Log.d("IROW ICELL", "$iRow, $iCell")
            val storageRef = Firebase.storage.reference
            val rasp = storageRef.child("raspisanie/rasp-2sem2022-4korp.xlsx")
            val localFile = File.createTempFile("rasp-2sem2022-4korp.xlsx", "xlsx")
            rasp.getFile(localFile).addOnSuccessListener {
                val file = FileInputStream(localFile)
                val workbook = XSSFWorkbook(file)

                for (i in 1..6) {

                    var scheduleM = Schedule("", "", "")
                    var scheduleTu = Schedule("", "", "")
                    var scheduleW = Schedule("", "", "")
                    var scheduleTh = Schedule("", "", "")
                    var scheduleF = Schedule("", "", "")
                    var scheduleS = Schedule("", "", "")

                    scheduleM.subject = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i))?.getCell(iCell).toString()
                    scheduleM.teacher = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 1)?.getCell(iCell).toString()
                    scheduleM.classRoom = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 1)?.getCell(iCell + 3).toString()
                    weekSchedule.MONDAY.add(scheduleM)

                    scheduleTu.subject = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 14)?.getCell(iCell).toString()
                    scheduleTu.teacher = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15)?.getCell(iCell).toString()
                    scheduleTu.classRoom = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15)?.getCell(iCell + 3).toString()
                    weekSchedule.TUESDAY.add(scheduleTu)

                    scheduleW.subject = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 14 * 2)?.getCell(iCell).toString()
                    scheduleW.teacher = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15 * 2 - 1)?.getCell(iCell).toString()
                    scheduleW.classRoom = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15 * 2 - 1)?.getCell(iCell + 3).toString()
                    weekSchedule.WEDNESDAY.add(scheduleW)

                    scheduleTh.subject = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 14 * 3)?.getCell(iCell).toString()
                    scheduleTh.teacher = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15 * 3 - 2)?.getCell(iCell).toString()
                    scheduleTh.classRoom = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15 * 3 - 2)?.getCell(iCell + 3).toString()
                    weekSchedule.THURSDAY.add(scheduleTh)

                    scheduleF.subject = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 14 * 4)?.getCell(iCell).toString()
                    scheduleF.teacher = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15 * 4 - 3)?.getCell(iCell).toString()
                    scheduleF.classRoom = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15 * 4 - 3)?.getCell(iCell + 3).toString()
                    weekSchedule.FRIDAY.add(scheduleF)

                    scheduleS.subject = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 14 * 5)?.getCell(iCell).toString()
                    scheduleS.teacher = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15 * 5 - 4)?.getCell(iCell).toString()
                    scheduleS.classRoom = workbook.getSheetAt(0)
                        .getRow(iRow + (2 * i) + 15 * 5 - 4)?.getCell(iCell + 3).toString()
                    weekSchedule.SATURDAY.add(scheduleS)

                }

//            val sheetName = workbook.getSheetAt(0).getRow(iRow).getCell(iCell)
//                Log.d("fillWeekSchedule", weekSchedule.WEDNESDAY[0].subject.toString())
                result.postValue(weekSchedule)
                closeScheduleFile(file)

            }
        }
        return result
    }
}

