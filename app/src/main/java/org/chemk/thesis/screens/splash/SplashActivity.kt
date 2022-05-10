package org.chemk.thesis.screens.splash

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import org.chemk.thesis.R
import org.chemk.thesis.screens.models.Repositories
import java.io.File

/**
 * Вход в приложение
 *
 * Содержит в себе лишь загрузочный фон, вся остальная логика расписана в:
 * [SplashFragment] and [SplashViewModel].
 */

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Repositories.init(applicationContext)
        super.onCreate(savedInstanceState)

////        val dirPath = "${getExternalStorageDirectoryPath}/Documents/APP name"
//        val path = getExternalFilesDir(null)
////        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//        path?.mkdirs()
//        val file = File(path, "raspisanie.xlsx")
//        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
//        val url =
//            Uri.parse("http://chemk.org/images/sampledata/docs/raspisanie/4korpus/rasp-2sem2022-4korp.xlsx")
//        val request = DownloadManager.Request(url)
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
//        request.setDestinationInExternalFilesDir(this, null, file.toString())
//        Log.d("SplActivityPath", file.toString())
//        downloadManager!!.enqueue(request)

        setContentView(R.layout.activity_splash)



    }


}