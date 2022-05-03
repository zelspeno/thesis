package org.chemk.thesis.screens.splash

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import org.chemk.thesis.R
import org.chemk.thesis.screens.models.Repositories

/**
 * Вход в приложение
 *
 * Содержит в себе лишь загрузочный фон, вся остальная логика расписана в:
 * [SplashFragment] and [SplashViewModel].
 */

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Repositories.init(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }


}