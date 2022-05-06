package org.chemk.thesis.screens.models

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import java.io.File
import java.io.FileInputStream

class HomeViewModel(private val accountsRepository: AccountsRepository) : ViewModel() {


    fun ex(context: File?) {

        val file = FileInputStream(File("$context/raspisanie.xlsx"))
        val workbook = HSSFWorkbook(file)
        val sheet0 = workbook.getSheetAt(0).sheetName
        Log.d("Excel parser:", "$sheet0")
        file.close()
    }
}