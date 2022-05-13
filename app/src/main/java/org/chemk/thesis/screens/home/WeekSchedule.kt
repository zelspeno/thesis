package org.chemk.thesis.screens.home

data class WeekSchedule(
    var MONDAY: MutableList<Schedule> = mutableListOf(),
    var TUESDAY: MutableList<Schedule> = mutableListOf(),
    var WEDNESDAY: MutableList<Schedule> = mutableListOf(),
    var THURSDAY: MutableList<Schedule> = mutableListOf(),
    var FRIDAY: MutableList<Schedule> = mutableListOf(),
    var SATURDAY: MutableList<Schedule> = mutableListOf(),
)