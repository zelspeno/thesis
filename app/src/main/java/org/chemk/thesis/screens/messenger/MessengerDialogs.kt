package org.chemk.thesis.screens.messenger

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class HostelData1: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Общежитие №1")
                .setMessage("Адрес: 428027, Чувашская Республика г. Чебоксары, пр. Ивана Яковлева, д.18/2 \n" +
                        "Телефон: (8352) 22-43-88, доб. 625")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}

class HostelData2: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Общежитие №2")
                .setMessage("Адрес: 428014, Чувашская Республика г. Чебоксары, ул. Магницкого, д.5 \n" +
                        "Телефон: (8352) 22-43-88, доб. 626")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}

class Campus1: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("1 корпус")
                .setMessage("Адрес: 428000, Чувашская Республика г. Чебоксары, пр. Ленина, 9 \n" +
                        "Вахта: (8352) 22-43-87, доб. 921 \n" +
                        "Деканат: (8352) 22-43-90, доб. 421 \n" +
                        "Учебная часть: (8352) 22-43-95, доб. 431 (по вопросам расписания)")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}

class Campus2: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("2 корпус")
                .setMessage("Адрес:  428024, Чувашская Республика г. Чебоксары, пр. Ивана Яковлева, д. 17 \n" +
                        "Вахта: (8352) 22-43-87, доб. 922 \n" +
                        "Деканат отделения младших курсов: (8352) 22-43-90, доб. 422 \n" +
                        "Деканат отделения старших курсов: (8352) 22-43-90, доб. 423 \n" +
                        "Учебная часть: (8352) 22-43-95, доб. 432 (по вопросам расписания)")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}

class Campus3: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("3 корпус")
                .setMessage("Адрес:  428027, Чувашская Республика г. Чебоксары, пр. Ивана Яковлева, д. 20 \n" +
                        "Вахта: (8352) 22-43-87, доб. 923 \n" +
                        "Деканат: (8352) 22-43-90, доб. 424 \n" +
                        "Учебная часть: (8352) 22-43-95, доб. 433 (по вопросам расписания)")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}

class Campus4: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("4 корпус")
                .setMessage("Адрес:  428022, г. Чебоксары, пр. Мира, д. 40 \n" +
                        "Вахта: (8352) 22-43-87, доб. 926 \n" +
                        "Вахта УПМ: (8352) 22-43-87, доб. 924 \n" +
                        "Деканат: (8352) 22-43-90, доб. 425 \n" +
                        "Учебная часть: (8352) 22-43-95, доб. 434 (по вопросам расписания)")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}

class Campus5: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("5 корпус")
                .setMessage("Адрес: 428028, г. Чебоксары, пр. Тракторостроителей, д. 99 \n" +
                        "Вахта: (8352) 22-43-87, доб. 925 \n" +
                        "Деканат отделения младших курсов: (8352) 22-43-90, доб. 426 \n" +
                        "Деканат отделения старших курсов: (8352) 22-43-90, доб. 427 \n" +
                        "Учебная часть: (8352) 22-43-95, доб. 435 (по вопросам расписания)")
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}