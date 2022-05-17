package org.chemk.thesis.screens.main

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentHomeBinding
import org.chemk.thesis.databinding.FragmentNewsBinding
import org.chemk.thesis.screens.home.HomeRecyclerAdapter
import org.chemk.thesis.screens.home.Schedule
import org.chemk.thesis.screens.home.WeekSchedule
import org.chemk.thesis.screens.models.HomeViewModel
import org.chemk.thesis.screens.models.Repositories
import org.chemk.thesis.screens.models.viewModelCreator
import org.chemk.thesis.screens.news.CustomRecyclerAdapter
import org.chemk.thesis.screens.utils.USER
import org.chemk.thesis.screens.utils.firebaseAuth
import org.chemk.thesis.screens.utils.initFirebase
import org.chemk.thesis.screens.utils.initUser
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModelCreator { HomeViewModel(Repositories.accountsRepository) }
    lateinit var wSchedule: WeekSchedule
    val groupsWRC = getGroupsWithRc()
    val day = getDayOfWeek()
    val tomorrow = getTomorrowOfWeek()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val sdf = SimpleDateFormat("dd.MM.YY")
        val currentDate = sdf.format(java.util.Calendar.getInstance().time)

        binding.frHomeTextView.text = "Расписание на $currentDate \n группа ${getGroup()}"

        val groups = getGroupsWithRc()
        val group = groups[getGroup()].toString()

        binding.scheduleTomorrowButton.setOnClickListener {
            val tomorrowDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.YY"))
            binding.frHomeTextView.text = "Расписание на $tomorrowDate (завтра) \n группа ${getGroup()}"
            getWeekSchedule(tomorrow, group)
        }

        binding.scheduleTodayButton.setOnClickListener {
            binding.frHomeTextView.text = "Расписание на $currentDate (сегодня) \n группа ${getGroup()}"
            getWeekSchedule(day, group)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun getGroupsWithRc(): Map<String, String> {

        val groups = mutableMapOf<String, String>()

        groups["С1-20"] = "C9"
        groups["С3-20"] = "G9"
        groups["Кс1-20"] = "K9"
        groups["Кс3-20"] = "O9"
        groups["Кс5-20"] = "S9"
        groups["Ип1-19"] = "W9"
        groups["Ип1-19"] = "W9"
        groups["Ип3-19"] = "AA9"
        groups["Ир1-19"] = "AE9"
        groups["Ир3-19"] = "AI9"
        groups["Ир5-19"] = "AM9"
        groups["Кс1-19"] = "AQ9"
        groups["Кс3-19"] = "AU9"
        groups["Кс5-19"] = "AY9"
        groups["С1-19"] = "BC9"
        groups["С3-19"] = "BG9"
        groups["С1-18"] = "DE9"
        groups["С3-18"] = "DI9"
        groups["С1-17"] = "DM9"

        return groups
    }

    private fun isPractice(group: String): Boolean {
        var count = 0
        var result = false

        for (i in groupsWRC.keys) {
            if (group == i) {
                result = false
            } else {
                count++
            }
        }
        if (count == groupsWRC.keys.size) {
            result = true
        }
        return result
    }


    private fun getGroup(): String = USER.group

    private fun getDayOfWeek(): String = LocalDate.now().dayOfWeek.name

    private fun getTomorrowOfWeek(): String = LocalDate.now().dayOfWeek.plus(1).name

    private fun getWeekSchedule(day: String, group: String) {
        viewModel.fillWeekSchedule(group).observe(viewLifecycleOwner) {

            val data = mutableListOf<Schedule>()

            if (isPractice(getGroup())) {
                data.add(Schedule("Производственная практика", "", ""))
            } else {

                val rc = groupsWRC[getGroup()].toString()
                viewModel.getRowCellByIndexes(rc, day)
                wSchedule = it
                when (day) {
                    "MONDAY" -> {
                        for (i in 0..5) {
                            val subject = wSchedule.MONDAY[i].subject.toString()
                            val teacher = wSchedule.MONDAY[i].teacher.toString()
                            val classRoom = wSchedule.MONDAY[i].classRoom.toString()
                            data.add(i, Schedule(subject, teacher, classRoom))
                            Log.d("$day Schedule [$i]", "$subject, $teacher, $classRoom")
                        }
                    }
                    "TUESDAY" -> {
                        for (i in 0..5) {
                            val subject = wSchedule.TUESDAY[i].subject.toString()
                            val teacher = wSchedule.TUESDAY[i].teacher.toString()
                            val classRoom = wSchedule.TUESDAY[i].classRoom.toString()
                            data.add(i, Schedule(subject, teacher, classRoom))
                            Log.d("$day Schedule [$i]", "$subject, $teacher, $classRoom")
                        }
                    }
                    "WEDNESDAY" -> {
                        for (i in 0..5) {
                            val subject = wSchedule.WEDNESDAY[i].subject.toString()
                            val teacher = wSchedule.WEDNESDAY[i].teacher.toString()
                            val classRoom = wSchedule.WEDNESDAY[i].classRoom.toString()
                            data.add(i, Schedule(subject, teacher, classRoom))
                            Log.d("$day Schedule [$i]", "$subject, $teacher, $classRoom")
                        }
                    }
                    "THURSDAY" -> {
                        for (i in 0..5) {
                            val subject = wSchedule.THURSDAY[i].subject.toString()
                            val teacher = wSchedule.THURSDAY[i].teacher.toString()
                            val classRoom = wSchedule.THURSDAY[i].classRoom.toString()
                            data.add(i, Schedule(subject, teacher, classRoom))
                            Log.d("$day Schedule [$i]", "$subject, $teacher, $classRoom")
                        }
                    }
                    "FRIDAY" -> {
                        for (i in 0..5) {
                            val subject = wSchedule.FRIDAY[i].subject.toString()
                            val teacher = wSchedule.FRIDAY[i].teacher.toString()
                            val classRoom = wSchedule.FRIDAY[i].classRoom.toString()
                            data.add(i, Schedule(subject, teacher, classRoom))
                            Log.d("$day Schedule [$i]", "$subject, $teacher, $classRoom")
                        }
                    }
                    "SATURDAY" -> {
                        for (i in 0..5) {
                            val subject = wSchedule.SATURDAY[i].subject.toString()
                            val teacher = wSchedule.SATURDAY[i].teacher.toString()
                            val classRoom = wSchedule.SATURDAY[i].classRoom.toString()
                            data.add(i, Schedule(subject, teacher, classRoom))
                            Log.d("$day Schedule [$i]", "$subject, $teacher, $classRoom")
                        }
                    }
                    "SUNDAY" -> {
                        data.add(0, Schedule("Выходной", "", ""))
                    }
                }
                Log.d("fillWeekSchedule", it.WEDNESDAY[0].subject.toString())
            }
            activity?.runOnUiThread {
                binding.scheduleRV.layoutManager = LinearLayoutManager(activity)
                binding.scheduleRV.adapter = HomeRecyclerAdapter(data)
                binding.scheduleRV.addItemDecoration(DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
                )
            }
        }
    }
}