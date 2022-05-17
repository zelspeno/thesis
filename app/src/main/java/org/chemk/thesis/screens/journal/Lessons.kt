package org.chemk.thesis.screens.journal

import android.content.Context
import android.content.res.Resources
import android.view.View


import org.chemk.thesis.R


class Lessons {


    val L_ENG: String = "Иностранный язык в профессиональной деятельности"
    val L_COMP_MOD: String = "Компьютерное моделирование"
    val L_TEST_PM: String = "МДК 01 02 Поддержка и тестирование программных модулей"
    val L_DEV_MOB: String = "МДК 01 03 Разработка мобильных приложений"
    val L_DEV_PA: String = "МДК 02 01 Технология разработки программного обеспечения"
    val L_COMP_WS: String = "МДК 13 01 Компетенция Ворлдскиллс Россия ИТ-решения для бизнеса на платформе 1С Предприятие 8"
    val L_MANAGEMENT: String = "Менеджмент в профессиональной деятельности"
    val L_PHILOSOPHY: String = "Основы философии"
    val L_PHYSICAL_LESSONS: String = "Физическая культура"


    fun getValuesList(): List<String> =
        mutableListOf(
            L_ENG,
            L_COMP_MOD,
            L_TEST_PM,
            L_DEV_MOB,
            L_DEV_PA,
            L_COMP_WS,
            L_MANAGEMENT,
            L_PHILOSOPHY,
            L_PHYSICAL_LESSONS
        )

}






