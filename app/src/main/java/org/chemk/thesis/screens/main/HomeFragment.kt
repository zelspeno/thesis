package org.chemk.thesis.screens.main

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentHomeBinding
import org.chemk.thesis.screens.models.HomeViewModel
import org.chemk.thesis.screens.models.ProfileViewModel
import org.chemk.thesis.screens.models.Repositories
import org.chemk.thesis.screens.models.viewModelCreator
import java.io.File
import java.io.FileInputStream


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModelCreator { HomeViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.moveToScheduleButton.setOnClickListener {
            val url = Uri.parse("http://chemk.org/images/sampledata/docs/raspisanie/4korpus/rasp-2sem2022-4korp.xlsx")
            val intent: Intent = Intent(Intent.ACTION_VIEW, url)
            context?.startActivity(intent)
        }

////        val context = context?.filesDir
//
//        val path = context?.getExternalFilesDir(null).toString()
////        val path = context?.filesDir
////        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//        val sheet0 = viewModel.ex(path.toString(), context)
//        binding.frHomeTextView.text = sheet0



    }

}