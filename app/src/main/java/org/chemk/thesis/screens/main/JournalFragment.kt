package org.chemk.thesis.screens.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentJournalBinding
import org.chemk.thesis.screens.database.accounts.AccountsRepository
import org.chemk.thesis.screens.exceptions.WrongData
import org.chemk.thesis.screens.exceptions.WrongJournal
import org.chemk.thesis.screens.home.HomeRecyclerAdapter
import org.chemk.thesis.screens.journal.GroupLessons
import org.chemk.thesis.screens.journal.JournalRecyclerAdapter
import org.chemk.thesis.screens.journal.MarkForDay
import org.chemk.thesis.screens.models.JournalViewModel
import org.chemk.thesis.screens.models.Repositories
import org.chemk.thesis.screens.models.viewModelCreator
import org.chemk.thesis.screens.utils.*
import java.io.File
import java.io.FileInputStream
import java.lang.NumberFormatException
import kotlin.random.Random

class JournalFragment : Fragment(R.layout.fragment_journal) {

    private lateinit var binding: FragmentJournalBinding
    private val viewModel by viewModelCreator { JournalViewModel(Repositories.accountsRepository) }
    private var listSubjects = mutableListOf<String>()

    var fullname = "${USER.surname} ${USER.name} ${USER.midname}"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJournalBinding.bind(view)

        initUser()


        val data = mutableListOf<MarkForDay>()

        val periodSt = binding.jPeriodST
        val subjectSt = binding.jSubjectST


        val periodPopupMenu = PopupMenu(activity?.applicationContext, periodSt)
        var subjectPopupMenu = PopupMenu(activity?.applicationContext, subjectSt)


        val showJournal = binding.showJournalBtn

        periodPopupMenu.inflate(R.menu.period_popupmenu)
        subjectPopupMenu.inflate(R.menu.subject_popupmenu)

        val listSubjectsClear = mutableListOf<String>()

        viewModel.getSubjectsByGroup(USER.group).observe(viewLifecycleOwner) {

            for (i in it.lessons) {
                if (i.value) {
                    Log.d("keys", i.key)
                    listSubjects += i.key
                }
            }

            for (j in 0 until listSubjects.size) {
                listSubjectsClear.add(listSubjects[j])
            }
        }



        periodSt.setOnClickListener {
            periodPopupMenu.show()
        }

        var check = 0
        subjectSt.setOnClickListener {
            if (check == 0) {
                for (i in 0 until listSubjects.size) {
                    subjectPopupMenu.menu.add(R.id.journalMenu, i, i, listSubjectsClear[i])
                    check++
                }
                subjectPopupMenu.show()
            } else {
                subjectPopupMenu.show()
            }

        }

        showJournal.setOnClickListener {
            activity?.runOnUiThread {
                binding.journalRV.layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                binding.journalRV.adapter = JournalRecyclerAdapter(data)
                binding.journalRV.addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.HORIZONTAL
                    )
                )
            }
        }

        val countSubjectItems = subjectPopupMenu.menu.size()

        periodPopupMenu.setOnMenuItemClickListener {
            binding.jPeriodST.text = it.title
            true
        }

        subjectPopupMenu.setOnMenuItemClickListener {

            binding.jSubjectST.text = it.title
            when(it.title) {
                "Компьютерное моделирование" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                "Иностранный язык в профессиональной деятельности" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                "МДК 01 02 Поддержка и тестирование программных модулей" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                "МДК 01 03 Разработка мобильных приложений" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                "МДК 02 01 Технология разработки программного обеспечения" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                "МДК 13 01 Компетенция Ворлдскиллс Россия ИТ-решения для бизнеса на платформе 1С Предприятие 8" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                "Менеджмент в профессиональной деятельности" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                "Основы философии" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                "Физическая культураи" -> {
                    viewModel.getMarks(
                        subject = it.title.toString(),
                        group = USER.group,
                        name = fullname
                    )
                        .observe(viewLifecycleOwner) {
                            fillJournal(it.marks, data)
                        }
                    true
                }
                else -> {
                    WrongJournal().show(childFragmentManager, "WrongData")
                    false
                }
            }
        }

    }


    fun getMarks(subject: String, group: String, name: String) {
        val storageRef = Firebase.storage.reference
        val rasp = storageRef.child("journal/$subject.xlsx")
        val localFile = File.createTempFile("marks", "xlsx")
        rasp.getFile(localFile).addOnSuccessListener {
            val file = FileInputStream(localFile)
            val workbook = XSSFWorkbook(file)
            var sheet = workbook.getSheet(group)
            val fullname = "${USER.surname} ${USER.name} ${USER.midname}"
            for (i in 2..6) {
                if (sheet.getRow(i).getCell(0).toString() == fullname) {

                }
            }

            }
    }


    fun fillJournal(listMarks: List<MarkForDay>, data: MutableList<MarkForDay>) {

        data.clear()
        for (i in 1 until listMarks.size) {
            if (listMarks[i].mark.isBlank()) {
                data.add(i-1, MarkForDay(i, ""))
            }
            else {
                try {
                    val intMark = listMarks[i].mark[0].toString().toInt()
                    data.add(i-1, MarkForDay(i, intMark.toString()))
                } catch (e: NumberFormatException) {
                    data.add(i - 1, MarkForDay(i, listMarks[i].mark))
                }
            }
        }
    }
}