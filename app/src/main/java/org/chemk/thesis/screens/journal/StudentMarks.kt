package org.chemk.thesis.screens.journal

data class StudentMarks (var group: String = "",
                         var name: String = "",
                         var marks: List<MarkForDay> = listOf())