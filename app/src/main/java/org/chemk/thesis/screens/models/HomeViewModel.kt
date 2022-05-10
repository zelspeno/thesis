package org.chemk.thesis.screens.models

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class HomeViewModel(private val accountsRepository: AccountsRepository) : ViewModel() {


    fun ex(path: String, context: Context?): String {


        val dirPath1 = "$path/raspisanie.xlsx"
        val dirPath = File(path, "raspisanie.xlsx")
        Log.d("exFunPath", dirPath.toString())

        val file = FileInputStream(dirPath)
//        val workbook = WorkbookFactory.create(file)
            val workbook = XSSFWorkbook(file)
        val sheet0 = workbook.getSheetAt(0).sheetName
        Log.d("Excel parser:", sheet0)
        file.close()

        return sheet0

    }

    fun movingToSchedule() {

    }
}